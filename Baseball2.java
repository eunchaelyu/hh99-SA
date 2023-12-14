import java.util.*;
public class Baseball2 {
    public static void main(String[] args) {
        // 1. 컴퓨터 랜덤 함수 생성, 랜덤 정렬한 ArrayList를 int배열로 변환
        Set<Integer> intSet = new HashSet<>(); // HashSet 으로 생성한 intSet 에 3번 돌면서 0~9까지의 수를 중복과 순서를 고려 하지 않고 랜덤 배열 한다.(HashSet 의 특징을 이용함)
        while (intSet.size() < 3) {
            int a = (int) (Math.random() * 10); // Math.random()함수는 0.xxxx ~ 0.9xxx(부동소수점 난수) 까지의 값을 반환하기 때문에 10을 곱해준다
            intSet.add(a);
        }
        ArrayList<Integer> computerArray = new ArrayList<>(intSet); //순번값(인덱스)로 값을 하나씩 조회 가능
        Collections.shuffle(computerArray); // shuffle을 사용해서 랜덤 정렬

        int[] computer = computerArray.stream().mapToInt(Integer::intValue).toArray(); // ArrayList computerArray를 int 배열로 변환
        System.out.println(Arrays.toString(computer));  //컴퓨터 랜덤 배열 출력 해보기
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        // 2. 사용자가 3개의 숫자 입력 시도
        int n = 0;
        Scanner sc = new Scanner(System.in);
        int[] user = new int[3]; //사용자값 초기설정 {0,0,0}

        while (true) { //while문 사용(전체)
            n++; //(올바른 형식으로) 시도할 때마다 카운트 업
            System.out.println(n + "번째 시도: ");

            String temp = sc.nextLine(); // 입력값을 int가 아닌 spring으로 받음(0이 앞에 나오더라도 인식하게 하기위해서)
            user = new int[temp.length()]; // temp 길이 만큼 int 배열 생성

            for (int i = 0; i < temp.length(); i++) {
                user[i] = temp.charAt(i) - '0'; //아스키코드 참조, temp에 있는값을 하나씩 user 배열 인덱스 순서대로 넣어줌.
            }
//            System.out.println(Arrays.toString(user)); //[int1,int2,int3] 사용자 입력값이 배열에 잘 들어가는지 확인
//            System.out.println(Arrays.stream(user).distinct().count());
            if (user.length == 3 && Arrays.stream(user).distinct().count() == 3){ // Arrays.stream() : 스트림생성, .distinct(): 중간연산, 중복 없앰, .count(): 최종연산, 남은 자리수 카운트셈
//                System.out.println("올바른 값을 입력했습니다");
            }else {
                n = 0;
                System.out.println("[!Error!] 다시 입력하세요. 입력 값은 3자리 수여야 하며 중복 값이 없어야 합니다");
            }
            // 3. 사용자의 입력값과 컴퓨터 랜덤값 비교 구문, 위치와 값 일치 여부에 따라 s++, b++
            if(n!=0) { //올바른 형식으로 입력했을 경우 n은 1으로 작동
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
                // 4. s,b 연산
                if (s > 0 && b == 0) {
                    System.out.println(s + "S");
                } else if (b > 0 && s == 0) {
                    System.out.println(b + "B");
                } else {
                    System.out.println(b + "B" + s + "S");
                }

                // 5. 스트라이크 3번일 경우 계산
                if (s == 3) {
                    System.out.println(n + "번 만에 맞히셨습니다.");
                    System.out.println("게임을 종료합니다.");
                    break; //스트라이크가 3번 나왔을 경우 break로 while문 끝냄.
                }
            }
        }
    }
}

