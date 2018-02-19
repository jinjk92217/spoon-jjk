public class Main {
    public static void main(String[] args) {
        System.out.println();
    }

    public void test1() {
        return true;
    }

    public void test2() {
        if (1 == 2) {
            return "result";
        }else {
            System.out.println();
        }
    }

    public void test3() {
        return true;
    }

    public void test4() {
        if (1 == 2) {
            String s = "";
            return true;
        }
    }

    public void test5() {
        String r = "";
        if (1 == 2) {
            String s = "";
            return true;
        }
    }

    public void test6() {
        String r = "";
        return true;
    }
}
