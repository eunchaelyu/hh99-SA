import java.util.*;
import java.util.stream.Stream;

// HashSet 으로 생성한 intSet 에 3번 돌면서 0~9까지의 수를 중복과 순서를 고려 하지 않고 랜덤 배열 한다.(HashSet 의 특징을 이용함)
public class Main {
    public static void main(String[] args) {
        // 1. 컴퓨터 랜덤 함수 생성, 랜덤 정렬한 ArrayList를 int배열로 변환
        Set<Integer> intSet = new HashSet<>();
        while (intSet.size() < 3) {
            int a = (int) (Math.random() * 10); // Math.random()함수는 0.xxxx ~ 0.9xxx(부동소수점 난수) 까지의 값을 반환하기 때문에 10을 곱해준다
            intSet.add(a);
        }
        ArrayList<Integer> computerArray = new ArrayList<>(intSet); //순번값(인덱스)로 값을 하나씩 조회 가능
        Collections.shuffle(computerArray); // 랜덤 정렬

        int[] computer = computerArray.stream().mapToInt(Integer::intValue).toArray(); // ArrayList를 int 배열로 변환
        System.out.println(Arrays.toString(computer));  //컴퓨터 랜덤 배열 출력
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        // 2. 사용자 3개의 숫자 입력 , 입력받은 int를 int배열로 변환
        int n = 0;
        Scanner sc = new Scanner(System.in);
        int[] user = new int[3];

        while (true) {
            n++;
            System.out.println(n + "번째 시도: ");

            String temp = sc.nextLine();
            user = new int[temp.length()];

            for (int i = 0; i < temp.length(); i++) {
                user[i] = temp.charAt(i) - '0';
            }
            System.out.println(Arrays.toString(user)); //[int1,int2,int3]

            System.out.println(Arrays.stream(user).distinct().count());
            if (user.length == 3 && Arrays.stream(user).distinct().count() == 3) {
//                System.out.println("올바른 값을 입력했습니다");
            } else {
                n = 0;
                System.out.println("다시 입력하세요. 입력 값은 3자리 수여야 하며 중복 값이 없어야 합니다");
            }

            if(n!=0) {
                int s = 0;
                int b = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (user[i] == computer[j]) {
                            if (i == j) {
                                s++;
                            } else b++;
                        }
                    }
                }

                if (s > 0 && b == 0) {
                    System.out.println(s + "S");
                } else if (b > 0 && s == 0) {
                    System.out.println(b + "B");
                } else {
                    System.out.println(b + "B" + s + "S");
                }

                if (s == 3) {
                    System.out.println(n + "번 만에 맞히셨습니다.");
                    System.out.println("게임을 종료합니다.");
//              sc.close();
                    break;
                }
            }
        }
    }
}

