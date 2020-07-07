package by.softeq.webcrawler;

import by.softeq.webcrawler.bean.ConfigParam;
import by.softeq.webcrawler.controller.Controller;
import by.softeq.webcrawler.controller.ControllerImpl;
import by.softeq.webcrawler.service.validator.InputDataValidator;

import java.util.Scanner;

/**
 * The type Web crawler starter.
 */
public class WebCrawlerStarter {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("WebCrawler began work.\nEnter url:");
        String address = scanner.nextLine();

        while (!InputDataValidator.isValidAddress(address.trim())) {
            System.out.println("Invalid address! Try again..");
            address = scanner.nextLine();
        }

        System.out.println("Enter comma-separated words for matches");
        String wordsToFind = scanner.nextLine();

        System.out.println("Enter number - search depth");
        int maxDepthOfCrawling = scanner.nextInt();
        while (maxDepthOfCrawling < 0) {
            System.out.println("Invalid number! Enter a number > or = 0!");
            maxDepthOfCrawling = scanner.nextInt();
        }

        System.out.println("Enter number - max pages to search");
        int maxPagesToFind = scanner.nextInt();
        while (maxPagesToFind < 1) {
            System.out.println("Invalid number! Enter a number > 0!");
            maxPagesToFind = scanner.nextInt();
        }


//        String url = "https://www.tesla.com/elon-musk";
//        String wordsToFind = "Tesla, Musk, Gigafactory, Elon Mask";
//        int maxDepthOfCrawling = 2;
//        int maxPagesToFind = 42;

        ConfigParam configParam = ConfigParam.newBuilder()
                .setUrl(address.trim())
                .setWordsToFind(wordsToFind)
                .setMaxDepthOfCrawling(maxDepthOfCrawling)
                .setMaxPagesToFind(maxPagesToFind)
                .build();

        Controller controller = new ControllerImpl();
        controller.startWebScrapping(configParam);

//        String url = "https://softeq.by";
//        String wordsToFind = "technology, future, possibility";
//        int maxDepthOfCrawling = 5;
//        int maxPagesToFind = 500;

    }
}
