import java.util.*;

class Solution {

    private static final int COLUMN = 0;
    private static final int BEAM = 1;

    private static final int DEMOLISH = 0;
    private static final int CONSTRUCT = 1;

    private Point[][] map;
    private ArrayList<Answer> answerList;

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer;
        answerList = new ArrayList<>();

        map = new Point[n + 1][n + 1];

        for (int[] build : build_frame) {
            int x = build[0];
            int y = build[1];
            int type = build[2];
            int mode = build[3];

            if (mode == CONSTRUCT) {
                if (type == COLUMN) {
                    if (y == 0) {
                        Column column = new Column(Structure.getFloor());
                        build(column, x, y);
                    } else {
                        if (Column.isConstructable(getPoint(x, y).getParents())) {
                            Column column = new Column(getPoint(x, y).getParents());
                            build(column, x, y);
                        }
                    }
                } else {
                    List<Structure> parents = new ArrayList<>(getPoint(x, y).getParents());
                    parents.addAll(getPoint(x + 1, y).getParents());
                    if (Beam.isConstructable(parents)) {
                        Beam beam = new Beam(parents);
                        build(beam, x, y);
                    }
                }
            } else {
                if (type == COLUMN) {
                    demolish(getPoint(x, y).column, x, y);
                } else {
                    demolish(getPoint(x, y).beam, x, y);
                }
            }
        }

        answer = new int[answerList.size()][3];
        Collections.sort(answerList);
        for (int i = 0; i < answerList.size(); i++) {
            Answer ans = answerList.get(i);
            answer[i][0] = ans.x;
            answer[i][1] = ans.y;
            answer[i][2] = ans.type;
        }

        return answer;
    }

    private void build(Structure structure, int x, int y) {
        edit(structure, CONSTRUCT, x, y);
        answerList.add(getAns(structure, x, y));
    }

    private void demolish(Structure structure, int x, int y) {
        if (structure.canDemolish()) {
            structure.demolish();
            demolishFromMap(structure, x, y);
        }
    }

    private void demolishFromMap(Structure structure, int x, int y) {
        edit(structure, DEMOLISH, x, y);
        answerList.remove(getAns(structure, x, y));
    }

    private void edit(Structure structure, int mode, int x, int y) {
        if (structure instanceof Column) {
            Column target = mode == CONSTRUCT ? (Column) structure : null;
            getPoint(x, y).setColumn(target);
            getPoint(x, y + 1).setEndOfColumn(target);
        } else {
            Beam target = mode == CONSTRUCT ? (Beam) structure : null;
            getPoint(x, y).setBeam(target);
            getPoint(x + 1, y).setEndOfBeam(target);
        }
    }

    private Answer getAns(Structure structure, int x, int y) {
        return new Answer(x, y, structure instanceof Beam ? BEAM : COLUMN);
    }

    private Point getPoint(int x, int y) {
        if (map[x][y] == null) {
            map[x][y] = new Point();
        }
        return map[x][y];
    }

    static class Point {
        private Column column;
        private Beam beam;
        private Column endOfColumn;
        private Beam endOfBeam;
        private List<Structure> parents;
        private boolean isDirty = true;

        public List<Structure> getParents() {
            if (isDirty || parents == null) {
                List<Structure> parents = new ArrayList<>();
                if (endOfBeam != null) {
                    parents.add(endOfBeam);
                }
                if (beam != null) {
                    parents.add(beam);
                }
                if (endOfColumn != null) {
                    parents.add(endOfColumn);
                }
                this.parents = parents;
                isDirty = false;
            }
            return parents;
        }

        public void setColumn(Column column) {
            this.column = column;
            isDirty = true;
        }

        public void setBeam(Beam beam) {
            this.beam = beam;
            isDirty = true;
        }

        public void setEndOfColumn(Column endOfColumn) {
            this.endOfColumn = endOfColumn;
            isDirty = true;
        }

        public void setEndOfBeam(Beam endOfBeam) {
            this.endOfBeam = endOfBeam;
            isDirty = true;
        }
    }

    static abstract class Structure {
        protected List<Structure> parents;
        protected List<Structure> children;

        private static Structure getFloor() {
            return new Structure() {

                @Override
                public boolean isValid(List<Structure> parents) {
                    return true;
                }
            };
        }

        private Structure() {
            parents = new ArrayList<>();
            children = new ArrayList<>();
        }

        protected Structure(Structure parent) {
            this();
            initParents(parent);
        }

        protected Structure(List<Structure> parents) {
            this();
            for (Structure parent : parents) {
                initParents(parent);
            }
        }

        private void initParents(Structure parent) {
            parents.add(parent);
            parent.addChild(this);
        }

        private void addChild(Structure child) {
            this.children.add(child);
        }

        public static boolean isConstructable(List<Structure> parents) {
            return !parents.isEmpty();
        }

        public abstract boolean isValid(List<Structure> parents);

        public boolean canDemolish() {
            for (Structure child : children) {
                List<Structure> temp = new ArrayList<>(child.parents);
                temp.remove(this);
                if (!child.isValid(temp)) {
                    return false;
                }
            }
            return true;
        }

        public void demolish() {
            for (Structure child : children) {
                child.parents.remove(this);
            }
            for (Structure parent : parents) {
                parent.children.remove(this);
            }
        }
    }

    static class Column extends Structure {
        public Column(Structure parent) {
            super(parent);
        }

        public Column(List<Structure> parents) {
            super(parents);
        }

        @Override
        public boolean isValid(List<Structure> parents) {
            return isConstructable(parents);
        }
    }

    static class Beam extends Structure {
        public Beam(Structure parent) {
            super(parent);
        }

        public Beam(List<Structure> parents) {
            super();
            for (Structure parent : parents) {
                parent.children.add(this);
                this.parents.add(parent);
                if (parent instanceof Beam) {
                    parent.parents.add(this);
                }
            }
        }

        public static boolean isConstructable(List<Structure> parents) {
            if (!Structure.isConstructable(parents)) {
                return false;
            } else {
                for (Structure parent : parents) {
                    if (parent instanceof Column) {
                        return true;
                    }
                }
                return parents.size() >= 2;
            }
        }

        @Override
        public boolean isValid(List<Structure> parents) {
            return isConstructable(parents);
        }
    }

    private static class Answer implements Comparable<Answer> {
        int x;
        int y;
        int type;

        public Answer(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Answer answer = (Answer) o;
            return x == answer.x &&
                    y == answer.y &&
                    type == answer.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, type);
        }

        @Override
        public int compareTo(Answer o) {
            if (x > o.x) {
                return 1;
            } else if (x < o.x) {
                return -1;
            } else {
                if (y > o.y) {
                    return 1;
                } else if (y < o.y) {
                    return -1;
                } else return Integer.compare(type, o.type);
            }
        }
    }
}
