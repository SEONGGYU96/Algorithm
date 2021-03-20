package com.gyugyu;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        String[] flag_rules = {"-s STRING", "-num NUMBER", "-e NULL", "-n ALIAS -num"};
        String[] commands = {"line -n 100 -s hi -e", "line -n 100 -e -num 150"};
        System.out.println(Arrays.toString(solution("line", flag_rules, commands)));
    }

    public static boolean[] solution(String program, String[] flag_rules, String[] commands) {
        int size = commands.length;
        boolean[] results = new boolean[size];
        Map<String, ArgumentType> flagRules = new HashMap<>();

        //빠른 탐색을 위해 Map 자료구조로 flag_rules를 저장
        initFlagRulesMap(flagRules, flag_rules);

        for (int index = 0; index < size; index++) {
            String command = commands[index];
            //command 마다 규칙을 검증
            results[index] = validateCommand(program, flagRules, command);
        }

        return results;
    }

    private static void initFlagRulesMap(Map<String, ArgumentType> flagRules, String[] flag_rules) {
        for (String flag_rule : flag_rules) {
            if (flag_rule.contains("ALIAS")) {
                StringTokenizer st = new StringTokenizer(flag_rule);
                String alias = st.nextToken();
                st.nextToken();
                String flag = st.nextToken();
                flagRules.put(alias, flagRules.get(flag));
                return;
            }
            StringTokenizer st = new StringTokenizer(flag_rule);
            String flag = st.nextToken();
            String type = st.nextToken();

            //flag 에 알맞은 ArgumentType 객체를 할당
            ArgumentType argumentType;
            switch (type) {
                case "STRING" -> argumentType = new StringType();
                case "NUMBER" -> argumentType = new NumberType();
                case "STRINGS" -> argumentType = new StringsType();
                case "NUMBERS" -> argumentType = new NumbersType();
                default -> argumentType = new NullType();
            }
            flagRules.put(flag, argumentType);
        }
    }

    private static boolean validateCommand(String program, Map<String, ArgumentType> flagRules, String command) {
        // 가장 처음에 위치한 문자열(program) 검색
        Matcher programMatcher = Pattern.compile("^[a-z]+").matcher(command);
        if (!programMatcher.find()) {
            return false;
        }
        // 주어진 program 과 동일한지 검증
        String _program = programMatcher.group();
        if (!_program.equals(program)) {
            return false;
        }
        // program 을 제외한 flag 하나와 argument 들을 묶어서 검색 (flag args1 args2...)
        Matcher flagAndArgumentsMatcher = Pattern.compile("-[a-z]+( [A-z0-9]+)*").matcher(command);
        boolean hasArgs = false;
        while (flagAndArgumentsMatcher.find()) {
            hasArgs = true;
            String flagAndArguments = flagAndArgumentsMatcher.group();
            //flag 에 대한 검증
            if (!validateFlag(flagRules, flagAndArguments)) {
                return false;
            }
        }
        return hasArgs;
    }

    private static boolean validateFlag(Map<String, ArgumentType> flagRules, String flagAndArguments) {
        String flag;
        String arguments;

        // flag 와 arguments 나누어 검색
        int firstSpaceIndex = flagAndArguments.indexOf(" ");
        if (firstSpaceIndex != -1) {
            flag = flagAndArguments.substring(0, firstSpaceIndex);
            arguments = flagAndArguments.substring(firstSpaceIndex + 1);
        } else {
            flag = flagAndArguments;
            arguments = "";
        }
        // 해당 flag 가 어떤 ArgumentType 을  갖는지 검색
        ArgumentType argumentType = flagRules.get(flag);
        // 미리 정의된 규칙을 따르는지 검증
        return argumentType.isValid(arguments);
    }


    private abstract static class ArgumentType {
        protected String regex;

        public ArgumentType() {
            this.regex = setRegex();
        }

        abstract String setRegex();

        boolean isValid(String arguments) {
            return arguments.matches(regex);
        }
    }

    private static class NullType extends ArgumentType {

        @Override
        String setRegex() {
            return "";
        }

        @Override
        boolean isValid(String arguments) {
            return arguments.isEmpty();
        }
    }

    private static class StringType extends ArgumentType {
        private static final String REGEX = "[A-z]+";

        @Override
        String setRegex() {
            return REGEX;
        }
    }

    private static class StringsType extends StringType {

        @Override
        boolean isValid(String arguments) {
            String[] args = arguments.split(" ");
            for (String arg : args) {
                if (!super.isValid(arg)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class NumberType extends ArgumentType {
        private static final String REGEX = "[0-9]+";

        @Override
        String setRegex() {
            return REGEX;
        }
    }

    private static class NumbersType extends NumberType {
        @Override
        boolean isValid(String arguments) {
            String[] args = arguments.split(" ");
            for (String arg : args) {
                if (!super.isValid(arg)) {
                    return false;
                }
            }
            return true;
        }
    }
}
