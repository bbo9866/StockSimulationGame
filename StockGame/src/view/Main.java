package view;

import controller.MainController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🎯 [목표 수익률 주식 시뮬레이션 게임]");
        System.out.print("▶ 목표 수익률을 입력하세요 (%): ");
        double goalPercent = sc.nextDouble();

        System.out.print("▶ 도전할 턴 수를 입력하세요: ");
        int maxTurns = sc.nextInt();

        double initialCash = 1_000_000; // 초기 자본: 100만원

        System.out.println("\n초기 자본: " + (int) initialCash + "원");
        System.out.println(maxTurns + "턴 안에 " + goalPercent + "% 수익률을 달성해보세요!");
        System.out.println("게임을 시작합니다...\n");

        MainController controller = new MainController(initialCash, goalPercent, maxTurns);
        controller.start();

        sc.close();
    }
}
