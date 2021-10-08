package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class GameSetting {

    private int[] randomNum = new int[3];
    private int[] userNum = new int[3];

    private int strike;	// 스트라이크 개수
    private int ball;	// 볼 개수

    public void makeRandomNum() {
        HashSet<Integer> numberSet = new HashSet<Integer>();

        while (numberSet.size() < 3) {
            numberSet.add(Randoms.pickNumberInRange(1, 9));
        }

        Iterator<Integer> it = numberSet.iterator();

        int i = 0;

        while (it.hasNext()) {
            randomNum[i++] = it.next().intValue();
        }
    }

    public void inputNum() {

        String inputStr;
        int s1 = 0;
        int s2 = 0;
        int s3 = 0;

        LinkedHashSet<Integer> integers = new LinkedHashSet<>();



        do {
            integers.clear();
            System.out.print("숫자를 입력해주세요:");
            inputStr = Console.readLine();

            for (int i = 0; i < inputStr.length(); i++) {
                integers.add(Character.getNumericValue(inputStr.charAt(i)));
            }

            if(integers.size() != 3) {
                System.out.println("중복된 값 또는 세자리 숫자가 아닙니다. 다시 입력하세요");
            }
//            if (s1 == s2 || s1 == s3 || s2 == s3) {
//                System.out.println("중복된 값이 있습니다. 다시 입력하세요.");
//            }

        } while (integers.size() != 3);

        Iterator<Integer> it = integers.iterator();

        int i=0;
        while (it.hasNext()) {
            userNum[i] = it.next().intValue();
            i++;
        }

//
//        for (int i = 0; i < userNum.length; i++) {
//            userNum = new int[]{s1, s2, s3};
//        }
    }

    public void ballCount(){
        strike = 0;
        ball = 0;

        for(int i=0; i<randomNum.length; i++){
            for(int j=0; j<userNum.length; j++){
                if(randomNum[i] == userNum[j]){  // 값이 같은지 비교
                    if(i==j){ // 값이 같고 첨자가 같으면 스트라이크
                        strike++;
                    }
                    if(i!=j){ // 값은 같은데 첨자가 다르면 볼
                        ball++;
                    }
                }
            }
        }
    }

    // 게임을 시작하는 메서드
    public void gameStart(){
        // 난수를 만드는 메서드 호출
        makeRandomNum();

        System.out.println("rand 1==" + randomNum[0] + randomNum[1] + randomNum[2]);

        int cnt = 0; // 몇번만에 맞췄는지를 저장하는 변수
        do{
            cnt++;
            inputNum(); // 사용자 입력 메서드 호출
            ballCount();  // 볼카운트하는 메서드 호출
            System.out.println(strike + "스트라이크" + ball + "볼");
        }while(strike!=3);  // 3 스트라이크가 될 때까지 반복

        System.out.println(cnt + "번째만에 맞췄군요.");

        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        int answer = Integer.parseInt(Console.readLine());

        if(answer == 1){
            gameStart();
        }

        if (answer == 2) {
            System.out.println("bye bye~~~!!");
        }
    }

}
