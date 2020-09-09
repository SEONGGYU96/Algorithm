//내가 처음 한 풀이
class Solution {
        public int solution(String skill, String[] skill_trees) {
            int answer = skill_trees.length;

            char[] parsedSkills = skill.toCharArray();
            int head;

            for (String skillTree : skill_trees) {
                char[] parsed = skillTree.toCharArray();
                head = 0;

                for (char skills : parsed) {
                    if (skill.contains(String.valueOf(skills))) {
                        if (parsedSkills[head] == skills) {
                            head++;
                        } else {
                            answer--;
                            break;
                        }
                    }
                    if (head == parsedSkills.length - 1) {
                        break;
                    }
                }
            }
            return answer;
        }
    }

//정규표현식 사용
class Solution {
        public int solution(String skill, String[] skill_trees) {
            int answer = skill_trees.length;

            for (String skill_tree : skill_trees) {
                if (skill.indexOf(skill_tree.replaceAll("[^" + skill + "]", "")) != 0) {
                    answer--;
                }
            }
            
            return answer;
        }
    }
