package chap_01;

import java.util.*;

public class Baseball1 {
    public static void main(String[] args) {
        // 1-2. 0~9까지 중복되지 않는 랜덤한 숫자를 생성하는 함수 호출
        int gameCount = 0;
        int[] randomNumberArr = getRandomNumberArr();
        System.out.println(Arrays.toString(randomNumberArr));
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        while (true) {
            ++gameCount;
            System.out.print(gameCount + "번째 시도: ");
            // 2-2. 사용자가 입력한 숫자를 저장하는 함수 호출
            int[] userNumberArr = getUserNumberArr(randomNumberArr);
            // 3-2. 볼과 스트라이크를 판단하는 함수 호출
            int[] countNumberArr = getCountNumberArr(randomNumberArr, userNumberArr);

            // 3-3. 볼과 스트라이크에 여부에 따라 문장 출력
            if (countNumberArr[0] == 3) {
                System.out.println(countNumberArr[0] + "S");
                System.out.println(gameCount + "번만에 맞히셨습니다.");
                System.out.println("게임을 종료합니다.");
                break;
            } else {
                if (countNumberArr[0] == 0 && countNumberArr[1] != 0) {
                    System.out.println(countNumberArr[1] + "B");
                } else if (countNumberArr[0] != 0 && countNumberArr[1] == 0) {
                    System.out.println(countNumberArr[0] + "S");
                } else {
                    System.out.println(countNumberArr[1] + "B" + countNumberArr[0] + "S");
                }
            }
        }
    }

    // 3-1. 볼과 스트라이크를 판단하는 함수 정의
    static int[] getCountNumberArr(int[] randomNumberArr, int[] userNumberArr) {
        int[] setCountNumberArr = new int[2];
        for (int i = 0; i < randomNumberArr.length; i++) {
            for (int j = 0; j < userNumberArr.length; j++) {
                if (randomNumberArr[i] == userNumberArr[j]) {
                    if (i == j) {
                        setCountNumberArr[0]++;
                    } else {
                        setCountNumberArr[1]++;
                    }
                }
            }
        }
        return setCountNumberArr;
    }

    // 2-1. 사용자가 입력한 숫자를 저장하는 함수 정의
    static int[] getUserNumberArr(int[] randomNumberArr) {
        int[] setUserNumberArr = new int[randomNumberArr.length];
        Scanner sc = new Scanner(System.in);
        String userStrNumber = sc.next();

        for (int i = 0; i < userStrNumber.length(); i++) {
            setUserNumberArr[i] = userStrNumber.charAt(i) - '0';
        }
        return setUserNumberArr;
    }

    // 1-1. 0~9까지 중복되지 않는 랜덤한 숫자를 생성하는 함수 정의
    static int[] getRandomNumberArr () {
        int[] setRandomNumberArr = new int[3];
        for (int i = 0; i < setRandomNumberArr.length; i++) {
            setRandomNumberArr[i] = ((int) (Math.random() * 10));
            for (int j = 0; j < i; j++) {
                if (setRandomNumberArr[i] == setRandomNumberArr[j]) {
                    i--;
                }
            }
        }
        return setRandomNumberArr;
    }
}
