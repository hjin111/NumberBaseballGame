import java.util.*;

public class Game {

    public static void main (String[] args) {

        boolean again = true;

        while( again ) {

            // 컴퓨터는 1 ~ 9 까지 서로 다른 임의의 수 3개를 선택 ( 중복 허용 X ) -> HashSet 사용
            HashSet<Integer> set = new HashSet<>();
            int n = 0;
            while (set.size() < 3) { // 만약 같은 값이 들어 오면 set은 저장을 하지 않으므로 set.size()가 증가하지 않으니 while문은 3보다 클 때까지 돈다.

                Random random = new Random();
                n = random.nextInt(9) + 1; // 0 + 1 <= random < 9 + 1 -> 1 <= random < 10
                set.add(n);
            } //

            ArrayList<Integer> list = new ArrayList<>(set);
            //System.out.println(list); // 컴퓨터 숫자 출력 ( 확인차 )

            while (true) {

                Scanner scanner = new Scanner(System.in);
                System.out.print("숫자를 입력해 주세요 : ");
                String a = scanner.next(); // 내가 입력한 숫자 먼저 문자열로 받기
                int[] input = new int[a.length()]; // 자료형이 int인 input 배열 생성
                for (int i = 0; i < a.length(); i++) {
                    input[i] += Integer.parseInt(String.valueOf(a.charAt(i))); // 각 위치의 문자를 가져 와서 char -> String -> int 로 바꾼 후 input 배열에 넣기
                }

                int strike = 0;
                int ball = 0;

                for (int i = 0; i < input.length; i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (input[i] == list.get(j)) {
                            if (i == j) { // 자리 까지 같을 때
                                strike++;
                            } else { // 자리는 같지 않고 값만 같을 때
                                ball++;
                            }
                        }
                    } // for j
                } // for i

                // 출력
                if (strike == 0 && ball == 0) {
                    System.out.println("낫싱");
                } else if (strike > 0 && ball > 0) {
                    System.out.printf("%d스트라이크 %d볼\n", strike, ball);
                } else if (strike > 0) {
                    System.out.printf("%d스트라이크\n", strike);
                } else if (ball > 0) {
                    System.out.printf("%d볼\n", ball);
                }

                // 3 스트라이크인 경우
                if (strike == 3) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료.");
                    System.out.println(list); // 컴퓨터 숫자 출력
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.print("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                    int result = scanner1.nextInt();
                    if (result != 1) { // 1 -> again이 true여서 게임 재시작
                        again = false;
                    }
                    break;
                }

            } // while

        } // again while

    } // main

} // class

// 같은 수가 같은 자리에 있으면 스트라이크
// 같은 수가 다른 자리에 있으면 볼
// 같은 수가 전혀 없으면 포볼 또는 낫싱