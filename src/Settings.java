public class Settings {
    private static final String PATH = "./resources/";
    private static final String FILENAME_CORRECT = "kvetiny.txt";
    private static final String FILENAME_OUT = "kvetiny_out.txt";

    private static final String FILENAME_WRONG_DATE = "kvetiny-spatne-datum.txt";

    private static final String FILENAME_WRONG_FREQUENCY = "kvetiny-spatne-frekvence.txt";

    public static String getPATH() {
        return PATH;
    }

    public static String getFilenameCorrect() {
        return FILENAME_CORRECT;
    }

    public static String getFilenameWrongDate() {
        return FILENAME_WRONG_DATE;
    }

    public static String getFilenameWrongFrequency() {
        return FILENAME_WRONG_FREQUENCY;
    }
    public static String getFilenameOut() {
        return FILENAME_OUT;
    }

}
