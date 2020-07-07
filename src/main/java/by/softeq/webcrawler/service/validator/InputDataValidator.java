package by.softeq.webcrawler.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDataValidator {

    public static boolean isValidAddress(String address) {
        if (address.startsWith("www"))
            address = "http://" + address;

        Pattern pattern = Pattern.compile("^(https?|ftp|file|www)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();

    }
}
