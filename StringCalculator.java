import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {

        if (numbers.equals(""))
            return 0;


        if(numbers.contains(",\n")) {
            System.out.println("wrong delimiter");
            return 0;
        }


        Matcher matcher = Pattern.compile("//(.*)\\\\n(.*)").matcher(numbers);
        String delimiter;


        if (matcher.matches()) {
            delimiter = createDelimiter(matcher.group(1));
            numbers = matcher.group(2);
        } else {
            delimiter = ",|\\\\n";
        }


        int[] values = convert(numbers.split(delimiter));


        if (hasNegativeNumber(values)) {

            print(values);
            return 0;
        }


        return getSum(values);
    }

    private String createDelimiter(String delimiter) {
        StringBuilder result = new StringBuilder();


        if (delimiter.contains("[") && delimiter.contains("]")) {
            Pattern p = Pattern.compile("\\[(.*?)\\]");
            Matcher m = p.matcher(delimiter);

            while(m.find()) {
                StringBuilder str = new StringBuilder();

                for (int i = 0; i < m.group(1).length(); i++) {
                    str.append("\\").append(m.group(1).charAt(i));
                }

                str.append("|");

                result.append(str);
            }

            result.deleteCharAt(result.length() - 1);
        } else {
            for (int i = 0; i < delimiter.length(); i++) {
                result.append("\\").append(delimiter.charAt(i));
            }
        }

        return result.toString();
    }


    private int[] convert(String[] values) {
        int[] result = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = Integer.parseInt(values[i]);
        }

        return result;
    }


    private boolean hasNegativeNumber(int[] values) {
        for (int value: values) {
            if (value < 0) return true;
        }

        return false;
    }


    private void print(int[] values) {
        System.out.println("negative values:");
        for (int value: values) {
            if (value < 0)
                System.out.println(value);
        }
    }


    private int getSum(int[] values) {
        int sum = 0;

        for (int value: values) {

            if (value <= 1000)
                sum += value;

        }

        return sum;
    }
}