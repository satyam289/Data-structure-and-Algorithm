package Array;

import java.util.LinkedHashMap;
import java.util.Map;

class IntegerToRoman {

    public static String intToRomanBest(int num) {
        String o[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        String t[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        String c[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        String m[] = { "", "M", "MM", "MMM" };
        return m[num / 1000] + c[(num % 1000) / 100] + t[(num % 100) / 10] + o[num % 10];
    }

    public static String intToRomanBetter(int num) {
        //LinkedHashMap for insertion order or we can use TreeMap. For Ex: Map<K, V> treeMap = new TreeMap<>(Collections.reverseOrder());
        Map<Integer, String> map = new LinkedHashMap<Integer, String>(); 
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        String result = "";
        for (int i : map.keySet()) {
            while (num >= i) {
                result += map.get(i);
                num -= i;
            }
        }
        return result;
    }
   
    public static String intToRomanNaive2(int A) {
        String ans = "";
        while (A > 0) {
            if (A - 1000 >= 0) {
                ans += "M";
                A -= 1000;
            } else if (A - 900 >= 0) {
                ans += "CM";
                A -= 900;
            } else if (A - 500 >= 0) {
                ans += "D";
                A -= 500;
            } else if (A - 400 >= 0) {
                ans += "CD";
                A -= 400;
            } else if (A - 100 >= 0) {
                ans += "C";
                A -= 100;
            } else if (A - 90 >= 0) {
                ans += "XC";
                A -= 90;
            } else if (A - 50 >= 0) {
                ans += "L";
                A -= 50;
            } else if (A - 40 >= 0) {
                ans += "XL";
                A -= 40;
            } else if (A - 10 >= 0) {
                ans += "X";
                A -= 10;
            } else if (A - 9 >= 0) {
                ans += "IX";
                A -= 9;
            } else if (A - 5 >= 0) {
                ans += "V";
                A -= 5;
            } else if (A - 4 >= 0) {
                ans += "IV";
                A -= 4;
            } else {
                ans += "I";
                A -= 1;
            }
        }
        return ans;
    }

    public static String intToRomanNaive(int a) {
        StringBuilder sb = new StringBuilder();
        while (a > 5) {
            if (a / 1000 != 0) {

                int p = a / 1000;
                int q = a % 1000;
                for (int i = 0; i < p; i++) {
                    sb = sb.append(value(1000));
                }
                a = q;
            } else if (a / 500 != 0) {
                if (a >= 900 && a < 1000) {
                    sb.append("CM");
                    a = a % 900;
                }

                else {
                    int p = a / 500;
                    int q = a % 500;
                    for (int i = 0; i < p; i++) {
                        sb = sb.append(value(500));
                    }
                    a = q;
                }
            }

            else if (a / 100 != 0) {
                if (a >= 400 && a < 500) {
                    sb.append("CD");
                    a = a % 400;
                }

                else {
                    int p = a / 100;
                    int q = a % 100;
                    for (int i = 0; i < p; i++) {
                        sb = sb.append(value(100));
                    }
                    a = q;
                }
            }

            else if (a / 50 != 0) {
                if (a >= 90 && a < 100) {
                    sb.append("XC");
                    a = a % 90;
                } else {
                    int p = a / 50;
                    int q = a % 50;
                    for (int i = 0; i < p; i++) {
                        sb = sb.append(value(50));
                    }
                    a = q;
                }
            }

            else if (a / 10 != 0) {
                if (a >= 40 && a < 50) {
                    sb.append("XL");
                    a = a % 40;
                } else {
                    int p = a / 10;
                    int q = a % 10;
                    for (int i = 0; i < p; i++) {
                        sb = sb.append(value(10));
                    }
                    a = q;
                }
            }

            else if (a / 5 != 0) {
                if (a == 9) {
                    sb.append("IX");
                    return sb.toString();

                } else {
                    int p = a / 5;
                    int q = a % 5;
                    for (int i = 0; i < p; i++) {
                        sb = sb.append(value(5));
                    }
                    a = q;
                }
            }
        }
        if (a == 0) {
            return sb.toString();
        }
        sb.append(value(a));
        return sb.toString();
    }

    private static String value(int x) {
        if (x == 1) {
            return "I";
        }
        if (x == 2) {
            return "II";
        }
        if (x == 3) {
            return "III";
        }
        if (x == 4) {
            return "IV";
        }
        if (x == 5) {
            return "V";
        }
        if (x == 10) {
            return "X";
        }
        if (x == 50) {
            return "L";
        }
        if (x == 100) {
            return "C";
        }
        if (x == 500) {
            return "D";
        }
        if (x == 1000) {
            return "M";
        }
        return null;
    }

    public static void main(String [] args){
        System.out.println("Fastest : " + intToRomanBest(45));
        System.out.println("Optimal : " + intToRomanBetter(45));
        System.out.println("Brute Force 1 : " + intToRomanNaive(45));
        System.out.println("Brute Force 2 : " + intToRomanNaive2(45));
    }
}