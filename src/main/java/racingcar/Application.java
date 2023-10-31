package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<Car> cars = createCars(); // 차 리스트 생성
        int tryCount = getTryCount(); // 시도 횟수

        for (int i = 0; i < tryCount; i++) { // 입력한 시도 횟수만큼 반복하여 게임, 출력
            race(cars); // 자동차 무작위 값에 따라 전진 여부를 결정(1회전)하고 업데이트
            printCurrentPositions(cars); // 차량 위치 업데이트 후 현재 자동차들의 위치를 출력
        }
    }

    // 사용자로부터 입력받은 자동차 이름을 리스트에 추가하여 반환하는 메서드
    private static List<Car> createCars() {
        System.out.println("경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();
        String[] carNames = input.split(",");
        List<Car> cars = new ArrayList<>();

        for (String name : carNames) {
            validateCarName(name);
            cars.add(new Car(name.trim()));
        }

        return cars;
    }

    // 입력받은 자동차 이름이 유효한지 검증하는 메서드
    private static void validateCarName(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
        }
    }

    // 사용자로부터 시도 횟수를 입력받아 반환하는 메서드
    private static int getTryCount() {
        System.out.println("시도할 회수는 몇 회인가요?");
        String input = Console.readLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    // 주어진 자동차 리스트에 대해 무작위 값에 따라 전진 여부를 결정하고 업데이트하는 메서드
    private static void race(List<Car> cars) {
        for (Car car : cars) { // 차 수만큼 반복 1회전
            car.move(Randoms.pickNumberInRange(0, 9));
        }
    }

    // 현재 자동차들의 위치를 출력하는 메서드, 차량 수만큼
    private static void printCurrentPositions(List<Car> cars) {
        System.out.println();
        for (Car car : cars) {
            System.out.println(car.getName() + " : " + "-".repeat(car.getPosition()));
        }
        System.out.println();
    }
}

// 자동차를 나타내는 클래스
class Car {
    private final String name; // 자동차 이름
    private int position; // 현재 위치

    // 생성자: 자동차의 이름을 받아 초기 위치를 0으로 설정
    public Car(String name) {
        this.name = name;
        this.position = 0;
    }
    // 주사위를 굴려 나온 숫자에 따라 위치를 변경
    public void move(int number) {
        if (number >= 4) {
            this.position++;
        }
    }

    // 자동차 이름 반환
    public String getName() {
        return name;
    }

    // 현재 위치 반환
    public int getPosition() {
        return position;
    }
}
