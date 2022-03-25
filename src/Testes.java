import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

public class Testes {
    private static final Date[] CUMULATIVE_DATE_DEFAULT = {new Date(121, 8, 30), new Date(121, 9, 1), new Date(121, 9, 2), new Date(121, 9, 3), new Date(121, 9, 4), new Date(121, 9, 5), new Date(121, 9, 6), new Date(121, 9, 7), new Date(121, 9, 8), new Date(121, 9, 9), new Date(121, 9, 10), new Date(121, 9, 11), new Date(121, 9, 12), new Date(121, 9, 13), new Date(121, 9, 14), new Date(121, 9, 15), new Date(121, 9, 16), new Date(121, 9, 17), new Date(121, 9, 18), new Date(121, 9, 19), new Date(121, 9, 20), new Date(121, 9, 21), new Date(121, 9, 22), new Date(121, 9, 23), new Date(121, 9, 24), new Date(121, 9, 25), new Date(121, 9, 26), new Date(121, 9, 27), new Date(121, 9, 28), new Date(121, 9, 29), new Date(121, 9, 30), new Date(121, 9, 31), new Date(121, 10, 1), new Date(121, 10, 2), new Date(121, 10, 3), new Date(121, 10, 4), new Date(121, 10, 5), new Date(121, 10, 6), new Date(121, 10, 7), new Date(121, 10, 8), new Date(121, 10, 9), new Date(121, 10, 10), new Date(121, 10, 11), new Date(121, 10, 12), new Date(121, 10, 13), new Date(121, 10, 14), new Date(121, 10, 15), new Date(121, 10, 16), new Date(121, 10, 17), new Date(121, 10, 18), new Date(121, 10, 19), new Date(121, 10, 20), new Date(121, 10, 21), new Date(121, 10, 22), new Date(121, 10, 23), new Date(121, 10, 24), new Date(121, 10, 25), new Date(121, 10, 26), new Date(121, 10, 27), new Date(121, 10, 28), new Date(121, 10, 29), new Date(121, 10, 30), new Date(121, 11, 1), new Date(121, 11, 2), new Date(121, 11, 3), new Date(121, 11, 4), new Date(121, 11, 5), new Date(121, 11, 6), new Date(121, 11, 7), new Date(121, 11, 8), new Date(121, 11, 9), new Date(121, 11, 10), new Date(121, 11, 11), new Date(121, 11, 12), new Date(121, 11, 13), new Date(121, 11, 14), new Date(121, 11, 15), new Date(121, 11, 16), new Date(121, 11, 17), new Date(121, 11, 18), new Date(121, 11, 19), new Date(121, 11, 20), new Date(121, 11, 21), new Date(121, 11, 22), new Date(121, 11, 23), new Date(121, 11, 24), new Date(121, 11, 25), new Date(121, 11, 26), new Date(121, 11, 27), new Date(121, 11, 28), new Date(121, 11, 29), new Date(121, 11, 30), new Date(121, 11, 31)};
    private static final Date[] TOTAL_DATE_DEFAULT = {new Date(120, 10, 1), new Date(120, 10, 2), new Date(120, 10, 3), new Date(120, 10, 4), new Date(120, 10, 5), new Date(120, 10, 6), new Date(120, 10, 7), new Date(120, 10, 8), new Date(120, 10, 9), new Date(120, 10, 10), new Date(120, 10, 11), new Date(120, 10, 12), new Date(120, 10, 13), new Date(120, 10, 14), new Date(120, 10, 15), new Date(120, 10, 16), new Date(120, 10, 17), new Date(120, 10, 18), new Date(120, 10, 19), new Date(120, 10, 20), new Date(120, 10, 21), new Date(120, 10, 22), new Date(120, 10, 23), new Date(120, 10, 24), new Date(120, 10, 25), new Date(120, 10, 26), new Date(120, 10, 27), new Date(120, 10, 28), new Date(120, 10, 29), new Date(120, 10, 30), new Date(120, 11, 1), new Date(120, 11, 2), new Date(120, 11, 3), new Date(120, 11, 4), new Date(120, 11, 5), new Date(120, 11, 6), new Date(120, 11, 7), new Date(120, 11, 8), new Date(120, 11, 9), new Date(120, 11, 10), new Date(120, 11, 11), new Date(120, 11, 12), new Date(120, 11, 13), new Date(120, 11, 14), new Date(120, 11, 15), new Date(120, 11, 16), new Date(120, 11, 17), new Date(120, 11, 18), new Date(120, 11, 19), new Date(120, 11, 20), new Date(120, 11, 21), new Date(120, 11, 22), new Date(120, 11, 23), new Date(120, 11, 24), new Date(120, 11, 25), new Date(120, 11, 26), new Date(120, 11, 27), new Date(120, 11, 28), new Date(120, 11, 29), new Date(120, 11, 30), new Date(120, 11, 31), new Date(121, 0, 1), new Date(121, 0, 2), new Date(121, 0, 3), new Date(121, 0, 4), new Date(121, 0, 5), new Date(121, 0, 6), new Date(121, 0, 7), new Date(121, 0, 8), new Date(121, 0, 9), new Date(121, 0, 10), new Date(121, 0, 11), new Date(121, 0, 12), new Date(121, 0, 13), new Date(121, 0, 14), new Date(121, 0, 15), new Date(121, 0, 16), new Date(121, 0, 17), new Date(121, 0, 18), new Date(121, 0, 19), new Date(121, 0, 20), new Date(121, 0, 21), new Date(121, 0, 22), new Date(121, 0, 23), new Date(121, 0, 24), new Date(121, 0, 25), new Date(121, 0, 26), new Date(121, 0, 27), new Date(121, 0, 28), new Date(121, 0, 29), new Date(121, 0, 30), new Date(121, 0, 31), new Date(121, 1, 1)};
    private static final int[][] CUMULATIVE_DATA_DEFAULT = {{10102942, 1069279, 735449, 120982, 17975}, {10102947, 1069975, 735806, 121049, 17979}, {10101821, 1070665, 736143, 121114, 17986}, {10102400, 1071114, 736485, 121183, 17993}, {10097267, 1071307, 736836, 121251, 18000}, {10095344, 1072037, 737182, 121313, 18004}, {10092012, 1072537, 737531, 121373, 18008}, {10089344, 1073268, 737884, 121430, 18019}, {10085480, 1074109, 738216, 121482, 18027}, {10083544, 1074813, 738537, 121537, 18034}, {10084440, 1075312, 738871, 121592, 18041}, {10082928, 1075639, 739227, 121650, 18048}, {10080424, 1076358, 739572, 121706, 18056}, {10077454, 1077186, 739907, 121760, 18065}, {10075963, 1077963, 740228, 121816, 18071}, {10072344, 1078729, 740529, 121871, 18078}, {10080940, 1079341, 740814, 121930, 18088}, {10083785, 1079806, 741109, 121991, 18097}, {10082127, 1080097, 741421, 122053, 18100}, {10079363, 1080929, 741720, 122113, 18106}, {10077865, 1081856, 742006, 122171, 18109}, {10077822, 1082721, 742294, 122229, 18117}, {10076442, 1083651, 742578, 122289, 18125}, {10076209, 1084534, 742852, 122344, 18129}, {10079654, 1085138, 743121, 122396, 18133}, {10079500, 1085451, 743411, 122455, 18138}, {10077765, 1086280, 743712, 122517, 18141}, {10077797, 1087245, 744028, 122578, 18144}, {10079808, 1088133, 744346, 122638, 18149}, {10078877, 1088977, 744677, 122703, 18153}, {10078937, 1089888, 744995, 122767, 18156}, {10084542, 1090651, 745330, 122826, 18157}, {10083660, 1091142, 745690, 122886, 18162}, {10085468, 1091592, 746062, 122945, 18171}, {10085587, 1092666, 746446, 123012, 18180}, {10085740, 1094048, 746803, 123085, 18184}, {10084722, 1095337, 747148, 123151, 18193}, {10084815, 1096534, 747471, 123213, 18198}, {10088613, 1097557, 747812, 123277, 18203}, {10086714, 1098125, 748172, 123339, 18209}, {10088451, 1099307, 748533, 123399, 18217}, {10087547, 1100961, 748913, 123461, 18222}, {10088080, 1102438, 749296, 123525, 18231}, {10086831, 1104189, 749707, 123590, 18234}, {10087495, 1106005, 750131, 123659, 18242}, {10090673, 1107488, 750596, 123734, 18257}, {10089679, 1108462, 751066, 123810, 18265}, {10088699, 1110155, 751552, 123890, 18274}, {10088116, 1112682, 752066, 123965, 18283}, {10088492, 1115080, 752589, 124037, 18295}, {10087525, 1117451, 753117, 124116, 18300}, {10087646, 1119784, 753661, 124204, 18310}, {10090489, 1122283, 754258, 124293, 18321}, {10089610, 1123758, 754886, 124386, 18339}, {10089462, 1126318, 755535, 124479, 18353}, {10088009, 1130091, 756216, 124584, 18370}, {10088295, 1133241, 756907, 124687, 18385}, {10089722, 1136446, 757612, 124787, 18393}, {10089469, 1139810, 758320, 124891, 18405}, {10092299, 1142707, 759084, 124995, 18417}, {10089505, 1144342, 759893, 125106, 18430}, {10085199, 1147249, 760726, 125222, 18441}, {10082673, 1151919, 761567, 125338, 18458}, {10080853, 1154817, 762483, 125466, 18471}, {10079660, 1157352, 763385, 125595, 18492}, {10077038, 1163001, 764264, 125725, 18514}, {10076682, 1166787, 765175, 125859, 18537}, {10069736, 1169003, 766123, 125994, 18551}, {10063244, 1172420, 767059, 126127, 18572}, {10057408, 1177706, 767976, 126265, 18587}, {10053721, 1181294, 768937, 126407, 18610}, {10049014, 1185036, 769884, 126544, 18626}, {10046136, 1190409, 770836, 126686, 18645}, {10044813, 1194288, 771800, 126829, 18658}, {10038523, 1196602, 772794, 126973, 18673}, {10032745, 1200193, 773747, 127115, 18687}, {10028337, 1205993, 774714, 127265, 18698}, {10025758, 1211130, 775666, 127423, 18717}, {10019515, 1215774, 776609, 127570, 18741}, {10017180, 1220836, 777514, 127717, 18753}, {10016875, 1225102, 778445, 127862, 18778}, {10008508, 1227854, 779388, 128014, 18796}, {10000679, 1233608, 780292, 128167, 18812}, {9993849, 1242545, 781201, 128322, 18823}, {9988136, 1253094, 782094, 128470, 18840}, {9981365, 1266037, 782958, 128619, 18851}, {9979380, 1276053, 783815, 128771, 18861}, {9982293, 1279785, 784693, 128922, 18874}, {9976339, 1286119, 785607, 129072, 18890}, {9968892, 1303291, 786543, 129224, 18909}, {9966793, 1330158, 787514, 129375, 18921}, {9968418, 1358817, 788548, 129519, 18937}, {9966266, 1389646, 789572, 129664, 18955}};
    private static final int[][] TOTAL_DATA_DEFAULT = {{10102947, 60026, 2122, 284, 37}, {10101821, 60963, 2255, 294, 46}, {10102400, 60219, 2349, 320, 45}, {10097267, 65300, 2337, 325, 59}, {10095344, 67157, 2362, 320, 46}, {10092012, 70354, 2425, 340, 52}, {10089344, 72945, 2420, 366, 56}, {10085480, 76647, 2522, 378, 48}, {10083544, 78378, 2651, 391, 63}, {10084440, 77338, 2742, 382, 62}, {10082928, 78716, 2785, 391, 82}, {10080424, 81141, 2794, 383, 78}, {10077454, 84032, 2799, 388, 69}, {10075963, 85444, 2798, 413, 55}, {10072344, 88854, 2929, 415, 76}, {10080940, 80045, 3040, 426, 91}, {10083785, 77126, 3028, 431, 81}, {10082127, 78681, 3051, 432, 79}, {10079363, 81384, 3017, 458, 69}, {10077865, 82736, 3079, 481, 61}, {10077822, 82767, 3025, 485, 62}, {10076442, 83942, 3151, 491, 73}, {10076209, 84004, 3241, 498, 74}, {10079654, 80432, 3275, 506, 85}, {10079500, 80528, 3251, 517, 71}, {10077765, 82241, 3192, 516, 82}, {10077797, 82116, 3208, 526, 67}, {10079808, 80068, 3155, 529, 87}, {10078877, 80838, 3245, 536, 64}, {10078937, 80614, 3342, 525, 78}, {10084542, 75008, 3275, 521, 72}, {10083660, 75755, 3338, 525, 68}, {10085468, 73876, 3330, 525, 79}, {10085587, 73712, 3295, 526, 79}, {10085740, 73561, 3229, 517, 73}, {10084722, 74456, 3268, 514, 87}, {10084815, 74187, 3367, 513, 78}, {10088613, 70426, 3263, 499, 81}, {10086714, 72181, 3332, 504, 70}, {10088451, 70381, 3304, 509, 86}, {10087547, 71266, 3230, 507, 95}, {10088080, 70786, 3093, 503, 88}, {10086831, 71863, 3157, 513, 98}, {10087495, 71012, 3254, 513, 90}, {10090673, 67805, 3206, 506, 84}, {10089679, 68762, 3181, 486, 82}, {10088699, 69686, 3142, 494, 87}, {10088116, 70285, 3061, 484, 75}, {10088492, 69910, 2973, 485, 86}, {10087525, 70754, 3027, 483, 71}, {10087646, 70426, 3158, 502, 57}, {10090489, 67577, 3095, 508, 63}, {10089610, 68469, 2990, 511, 89}, {10089462, 68690, 2853, 505, 70}, {10088009, 70178, 2754, 504, 65}, {10088295, 69769, 2790, 513, 78}, {10089722, 68208, 2870, 504, 63}, {10089469, 68307, 2967, 503, 58}, {10092299, 65457, 2930, 486, 74}, {10089505, 68205, 2896, 487, 79}, {10085199, 72496, 2840, 482, 76}, {10082673, 74989, 2806, 483, 66}, {10080853, 76675, 2858, 492, 73}, {10079660, 77601, 3044, 500, 73}, {10077038, 80008, 3171, 510, 78}, {10076682, 80183, 3260, 512, 90}, {10069736, 87004, 3293, 513, 91}, {10063244, 93360, 3333, 514, 95}, {10057408, 98938, 3451, 536, 118}, {10053721, 102406, 3555, 540, 111}, {10049014, 106778, 3770, 558, 102}, {10046136, 109312, 3983, 567, 122}, {10044813, 110388, 4043, 599, 155}, {10038523, 116328, 4240, 596, 156}, {10032745, 121815, 4368, 611, 148}, {10028337, 125861, 4560, 622, 159}, {10025758, 128165, 4653, 638, 166}, {10019515, 134011, 4889, 647, 152}, {10017180, 135886, 5165, 664, 167}, {10016875, 135841, 5291, 670, 218}, {10008508, 143776, 5493, 681, 219}, {10000679, 151226, 5630, 702, 221}, {9993849, 157660, 5779, 715, 234}, {9988136, 162951, 5922, 720, 274}, {9981365, 169230, 6117, 742, 275}, {9979380, 170635, 6420, 767, 252}, {9982293, 167381, 6472, 765, 291}, {9976339, 172893, 6603, 783, 293}, {9968892, 180076, 6565, 782, 303}, {9966793, 181811, 6627, 806, 278}, {9968418, 179939, 6544, 843, 293}, {9966266, 181623, 6694, 858, 303}, {9968252, 179180, 6869, 865, 275}};
    private static final double[][] TRANSITION_MATRIX_DEFAULT = {{0.999500, 0.030000, 0.002000, 0.001000, 0.000000}, {0.000500, 0.960000, 0.004000, 0.015000, 0.000000}, {0.000000, 0.007000, 0.980000, 0.020000, 0.000000}, {0.000000, 0.003000, 0.010000, 0.950000, 0.000000}, {0.000000, 0.000000, 0.004000, 0.014000, 1.000000}};
    private static final Date DEFAULT_DATE = new Date(120, 10, 19);
    private static final Date DEFAULT_DATE_ADD_DAYS_EXPECTED = new Date(120, 10, 20);
    private static final Date DEFAULT_DATE_ADD_MONTHS_EXPECTED = new Date(120, 11, 19);
    private static final Date DEFAULT_DATE_COPY_CONTENT_INITIAL = new Date(121, 8, 30);
    private static final Date DEFAULT_DATE_COPY_CONTENT_FINAL = new Date(121, 9, 4);
    private static final int[] EXPECTED_COPY_CONTENT_ARRAY = {10102942, 10102947, 10101821, 10102400, 10097267};
    private static final int[] EXPECTED_DIFFERENCE_VECTOR_ARRAY = {0, 0, 0, 0, 0};
    private static final double EXPECTED_AVERAGE = 1.01014754E7;
    private static final double EXPECTED_STANDARD_DEVIATION = 2144.8299326520037;
    private static final String DATE_PATTERN_IS_VALID_DATE1 = "20-03-2020";
    private static final String DATE_PATTERN_IS_VALID_DATE2 = "2020-03-20";
    private static final String DATE_PATTERN_IS_VALID_DATE3 = "20/03/2020";
    private static final String FILE_NAME1 = "registoAcumuladoCasoEstudo.csv";
    private static final String FILE_NAME2 = "registoAcumuladoCaso.csv";
    private static final String FILE_NAME2_TXT = "registoAcumuladoCaso.txt";
    private static final String FILE_NAME2_WITHOUT_EXT = "registoAcumuladoCaso";
    private static final int[][] EXPECTED_VALUES_GET_ALL_TOTAL_VALUES = {{10102947, 60026, 2122, 284, 37}, {10101821, 60963, 2255, 294, 46}};
    private static final double[][] DEFAULT_MATRIX_TEST = {{1, 2}, {3, 4}};
    private static final double[][] EXPECTED_MATRIX_TEST = {{0, 0}, {0, 0}};
    private static final int DIARIO_NAO_INFETADO_INDEX = 1;
    private static final int CUMULATIVE_DATE_DEFAULT_LENGTH = CUMULATIVE_DATE_DEFAULT.length;
    private static final Date[] DEFAULT_INTERVAL_DATE = {TOTAL_DATE_DEFAULT[0], TOTAL_DATE_DEFAULT[1]};
    private static final double[][] MATRIX_N = {{16421.965317933784, 14421.965317931976, 8567.630057811122, 8082.08092486271},{231.21387283257678, 231.2138728325513, 132.94797687872028, 127.16763005790493},{118.49710982669544, 118.49710982668236, 130.635838150344, 90.17341040467613},{37.572254335293664, 37.57225433528952, 34.10404624279199, 45.664739884409485}};
    private static final double[][] MATRIX_M = {{16809.24855492835, 14809.248554926498, 8865.317919082978, 8345.0867052097}};

    public static void main(String[] args) throws IOException, ParseException {
        runTestes();
    }

    private static boolean testCalculateIndexRange(Date[] dateArray, Date initialDate, Date finalDate, int arrayLength, int[] expectedIndexes) {
        if (Arrays.equals(Projeto02.calculateIndexRange(dateArray, initialDate, finalDate, arrayLength), expectedIndexes))
            return true;
        else
            return false;
    }

    private static boolean testVariation(int[][] dataArray, int finalIndex, int initialIndex, int dataType, int expectedVariation) {
        if (Projeto02.variation(dataArray, finalIndex, initialIndex, dataType) == expectedVariation)
            return true;
        else
            return false;
    }

    private static boolean testWeekIsComplete(Date initialDate, Date finalDate, boolean expectedResult) {
        if (Projeto02.weekIsComplete(initialDate, finalDate) == expectedResult)
            return true;
        else
            return false;
    }

    private static boolean testNumberOfWeeks(Date[] rangeDate, int expectedNumberOfWeeks) {
        if (Projeto02.numberOfWeeks(rangeDate) == expectedNumberOfWeeks)
            return true;
        else
            return false;
    }

    public static boolean testFindCompleteMonth(Date initialDate, Date finalDate, Date[] expectedDateRange) {
        if (Arrays.equals(Projeto02.findCompleteMonth(initialDate, finalDate), expectedDateRange))
            return true;
        else
            return false;
    }

    public static boolean testNumberOfMonths(Date[] rangeDate, int expectedNumberOfMonths) {
        if (Projeto02.numberOfMonths(rangeDate) == expectedNumberOfMonths)
            return true;
        else
            return false;
    }

    public static boolean testVariationIsPossibleInDayBefore(int resolution, Date initialDate, Date finalDate, Date[] dateArray, int arrayLength, boolean expectedResult) {
        if (Projeto02.variationIsPossibleInDayBefore(resolution, initialDate, finalDate, dateArray, arrayLength) == expectedResult)
            return true;
        else
            return false;
    }

    public static boolean testValidateResolution(String resolution, boolean resolutionAsExpected) {
        if (Projeto02.validateResolution(resolution) == (resolutionAsExpected))
            return true;
        else
            return false;
    }

    public static boolean testNumberOfDays(Date initialDate, Date finalDate, int expectedNumberOfDays) {
        if (Projeto02.numberOfDays(initialDate, finalDate) == expectedNumberOfDays)
            return true;
        else
            return false;
    }

    public static boolean testCalculateIndex(Date[] dateArray, Date date, int arrayLength, int expectedDateIndex) {
        if (Projeto02.calculateIndex(dateArray, date, arrayLength) == expectedDateIndex)
            return true;
        else
            return false;
    }

    private static boolean testAddDays(Date date, int i, Date expectedDate) {
        if (Projeto02.addDays(date, i).equals(expectedDate)) return true;
        return false;
    }

    private static boolean testAddMonth(Date defaultDate, int i, Date defaultDateAddMonthsExpected) {
        if (Projeto02.addMonth(defaultDate, i).equals(defaultDateAddMonthsExpected)) return true;
        return false;
    }

    private static boolean testCopyArrayContent(int[][] dataArray, Date[] dateArray, Date initialDate, Date finalDate, int arrayLength, int dataType, int[] expectedResult) {
        int[] actual = Projeto02.copyArrayContent(dataArray, dateArray, initialDate, finalDate, arrayLength, dataType);
        if (Arrays.equals(actual, expectedResult)) return true;
        return false;
    }

    private static boolean testCalculateAverage(int[] dataArray, double expectedAverage, boolean dayBefore) {
        if (Projeto02.calculateAverage(dataArray, dayBefore) == expectedAverage) return true;
        return false;
    }

    private static boolean testDifferenceVector(int[] dataArrayInitial, int[] dataArrayFinal, int[] expectedArray) {
        int[] actual = Projeto02.differenceVector(EXPECTED_COPY_CONTENT_ARRAY, EXPECTED_COPY_CONTENT_ARRAY);
        if (Arrays.equals(actual, expectedArray)) return true;
        return false;
    }

    private static boolean testCalculateStandardDeviation(double average, int[] dataArray, double expectedStandardDeviation, boolean dayBefore) {
        if (Projeto02.calculateStandardDeviation(average, dataArray, dayBefore) == expectedStandardDeviation) return true;
        return false;
    }

    private static boolean testIsValidDate(String date, int expectedValue) {
        if (Projeto02.isValidDate(date) == expectedValue) return true;
        return false;
    }

    private static boolean testValidateInputFile(String inputFile, boolean expected) {
        if (Projeto02.validateInputFile(inputFile) == expected) return true;
        return false;
    }

    private static boolean testGetAllTotalDataArrayRequiredValues(Date[] date, Date[] dateArray, int[][] dataArray, int arrayLength, int[][] expectedValue) {
        int[][] actual = Projeto02.getAllTotalArrayRequiredValues(date, dateArray, dataArray, arrayLength);
        if (Arrays.deepEquals(actual, expectedValue)) return true;
        return false;
    }

    private static boolean testSubtractMatrices(double[][] matrix1, double[][] matrix2, double[][] expectedResult) {
        double[][] actual = Projeto02.subtractMatrices(matrix1, matrix2);
        if (Arrays.deepEquals(actual, expectedResult)) return true;
        return false;
    }

    private static boolean testFillArray(Date[] dateArray, int[][] dataArray, String fileName, String option, int expectedResult) throws IOException, ParseException {
        if (Projeto02.fillArray(dateArray, dataArray, fileName, option) == expectedResult) return true;
        return false;
    }

    public static boolean testDailyFluctuation(Date initialDate, Date finalDate, int dataType, int[] expectedArray) {
        return (Arrays.equals(expectedArray, Projeto02.dailyFluctuation(CUMULATIVE_DATA_DEFAULT, CUMULATIVE_DATE_DEFAULT, initialDate, finalDate, CUMULATIVE_DATA_DEFAULT.length, dataType, true)));
    }

    public static boolean testWeeklyFluctuation(Date initialDate, Date finalDate, int dataType, int[] expectedArray, boolean dayBefore) {
        return (Arrays.equals(expectedArray, Projeto02.weeklyFluctuation(CUMULATIVE_DATA_DEFAULT, CUMULATIVE_DATE_DEFAULT, initialDate, finalDate, dataType, CUMULATIVE_DATA_DEFAULT.length, dayBefore)));
    }

    public static boolean testMonthlyFluctuation(Date initialDate, Date finalDate, int dataType, int[] expectedArray, boolean dayBefore) {
        return (Arrays.equals(expectedArray, Projeto02.monthlyFluctuation(CUMULATIVE_DATA_DEFAULT, CUMULATIVE_DATE_DEFAULT, initialDate, finalDate, dataType, CUMULATIVE_DATA_DEFAULT.length, dayBefore)));
    }

    public static boolean testFindCompleteWeeks(Date initialDate, Date finalDate, Date[] expectedArray) {
        return (Arrays.equals(expectedArray, Projeto02.findCompleteWeeks(initialDate, finalDate)));
    }

    public static boolean testMonthIsComplete(Date initialDate, Date finalDate, boolean expectedResult) {
        return (expectedResult == Projeto02.monthIsComplete(initialDate, finalDate));
    }

    public static boolean testDateRangeIsInArray(Date initialDate, Date finalDate, boolean expectedResult, Date[] dateArray) {
        return (expectedResult == Projeto02.dateRangeIsInArray(dateArray, initialDate, finalDate, dateArray.length));
    }

    public static boolean testValidateDates(Date initialDate, Date finalDate, boolean expectedResult) {
        return (expectedResult == Projeto02.validateDates(initialDate, finalDate));
    }

    public static boolean testVariationIsPossible(Date initialDate, Date finalDate, boolean expectedResult, int resolution) {
        return (expectedResult == Projeto02.variationIsPossible(resolution, initialDate, finalDate));
    }

    public static boolean testCalculateAllVariations(Date initialDate, Date finalDate, int resolution, int[][] expectedArray, boolean dayBefore) {
        return (Arrays.deepEquals(expectedArray, Projeto02.calculateAllVariations(resolution, CUMULATIVE_DATE_DEFAULT, CUMULATIVE_DATA_DEFAULT, initialDate, finalDate, CUMULATIVE_DATA_DEFAULT.length, dayBefore)));
    }

    public static boolean testDaysToDeathMatrixN(double[][] matrixQ, double[][] expectedArray) {
        return (Arrays.deepEquals(expectedArray, Projeto02.daysToDeathMatrixN(matrixQ)));
    }

    public static boolean testDaysToDeathMatrixM(double[][] matrixN, double[][] expectedArray) {
        return (Arrays.deepEquals(expectedArray, Projeto02.daysToDeathMatrixM(matrixN)));
    }

    public static boolean testPowerMatrix(double[][] matrix, int days, double[][] expectedMatrix){
        return Arrays.deepEquals(expectedMatrix, Projeto02.powerMatrix(matrix, days));
    }

    public static boolean testMultiplyMatrices(double[][] matrix1, double[][] matrix2, double[][] expectedMatrix){
        return Arrays.deepEquals(expectedMatrix, Projeto02.multiplyMatrices(matrix1, matrix2));
    }

    public static boolean testPredictDataInDay(Date predictionDate, double[][] expectedDataInDay){
        return (Arrays.deepEquals(expectedDataInDay, Projeto02.predictDataInDay(predictionDate, TOTAL_DATE_DEFAULT, TOTAL_DATA_DEFAULT.length, TRANSITION_MATRIX_DEFAULT, TOTAL_DATA_DEFAULT)));
    }

    public static boolean testHistoricData(int index, double[][] expectedHistoricDataForIndex){
        return (Arrays.deepEquals(expectedHistoricDataForIndex, Projeto02.historicData(index, TOTAL_DATA_DEFAULT)));
    }

    public static boolean testRemoveDeaths(double[][] matrixData, double[][] expectedMatrixDataWithoutDeaths){
        return (Arrays.deepEquals(expectedMatrixDataWithoutDeaths, Projeto02.removeDeaths(matrixData)));
    }

    public static boolean testLuDecomposition(double[][] matrix, double[][] expectedUpper, double[][] expectedLower){
        double[][][] luDecomposition = Projeto02.luDecomposition(matrix);
        return (Arrays.deepEquals(expectedUpper, luDecomposition[0]) && Arrays.deepEquals(expectedLower, luDecomposition[1]));
    }

    public static boolean testInvertLower(double[][] matrixLower, double[][] expectedInverseLower){
        return (Arrays.deepEquals(expectedInverseLower, Projeto02.invertLower(matrixLower)));
    }

    public static boolean testInvertUpper(double[][] matrixUpper, double[][] expectedInverseUpper){
        return (Arrays.deepEquals(expectedInverseUpper, Projeto02.invertUpper(matrixUpper)));
    }

    public static boolean testInvertMatrix(double[][] matrix, double[][] expectedInverseMatrix){
        return (Arrays.deepEquals(expectedInverseMatrix, Projeto02.invertMatrix(matrix)));
    }

    public static boolean testReadDayNIM(String dayToRead, Date expectedDateReaded) throws ParseException {
        return (expectedDateReaded.equals(Projeto02.readDayNIM(dayToRead)));
    }

    public static boolean testSameSizeIntervals(Date[] datesInterval1, Date[] datesInterval2, Date[] expectedDatesInterval1, Date[] expectedDatesInterval2){
        Date[] datesToValidate = new Date[]{datesInterval1[0], datesInterval1[1], datesInterval2[0], datesInterval2[1]};
        Projeto02.sameSizeIntervals(datesToValidate);
        return (Arrays.equals(datesToValidate, new Date[]{expectedDatesInterval1[0], expectedDatesInterval1[1], expectedDatesInterval2[0], expectedDatesInterval2[1]}));
    }

    public static boolean testDaysInString(Date initialDate, int requestedDataArrayLength, String[] expectedArray) {
        if (Arrays.equals(expectedArray, Projeto02.daysInString(initialDate, requestedDataArrayLength))) {
            return true;
        } else
            return false;
    }

    public static boolean testWeekIntervalsInString(Date initialDate,Date finalDate,String[][] arrayExpected) {
        if (Arrays.deepEquals(arrayExpected, Projeto02.weekIntervalsInString(initialDate, finalDate))) {
            return true;
        } else
            return false;
    }

    public static boolean testMonthInString(Date initalDate,int requestedDataArrayLength, String[] expectedArray) {
        if (Arrays.deepEquals(expectedArray, Projeto02.monthsInString(initalDate, requestedDataArrayLength))) {
            return true;
        } else
            return false;
    }

    public static boolean testDuplicateMatrix(double[][] matrix) {
        double[][] matrixDuplicated = Projeto02.duplicateMatrix(matrix);
        if (Arrays.deepEquals(matrixDuplicated, matrix)) {
            return true;
        }
        return false;
    }

    public static boolean testDateIsInArray(boolean expectedResult,Date date) {
        if (Projeto02.dateIsInArray(TOTAL_DATE_DEFAULT, date, TOTAL_DATA_DEFAULT.length) != expectedResult) {
            return true;
        } else
            return false;
    }

    public static boolean testCheckIfNull1(boolean expectedResult) {
        boolean test = Projeto02.checkIfNull(TOTAL_DATE_DEFAULT);
        if (test == expectedResult) {
            return true;
        } else
            return false;
    }

    public static boolean testCheckIfNull2(boolean expectedResult,double[][] matrix) {
        boolean test= Projeto02.checkIfNull(matrix);
        if (test == expectedResult) {
            return true;
        } else
            return false;
    }

    public static boolean testCorrectFileName (String fileName, int fileType, String expectedFileName) {
        return (expectedFileName.equals(Projeto02.correctFileName(fileName, fileType)));
    }

    public static void runTestes() throws IOException, ParseException {
        System.out.println("calculateIndexRange1:" + (testCalculateIndexRange(CUMULATIVE_DATE_DEFAULT, CUMULATIVE_DATE_DEFAULT[8], CUMULATIVE_DATE_DEFAULT[33], CUMULATIVE_DATE_DEFAULT.length, new int[]{8,33})?" OK":" NOT OK"+"\n"));
        System.out.println("calculateIndexRange2:" + (testCalculateIndexRange(CUMULATIVE_DATE_DEFAULT, new Date(120,9,8), new Date(127,10,2), CUMULATIVE_DATE_DEFAULT.length, new int[]{-1,-1})?" OK":" NOT OK"+"\n"));
        System.out.println("variation:" + (testVariation(CUMULATIVE_DATA_DEFAULT,3,0, 1, 1835)?" OK":" NOT OK"+"\n") );
        System.out.println("weekIsComplete1:" + (testWeekIsComplete(CUMULATIVE_DATE_DEFAULT[8], CUMULATIVE_DATE_DEFAULT[20], true)?" OK":" NOT OK"+"\n") );
        System.out.println("weekIsComplete2:" + (testWeekIsComplete(CUMULATIVE_DATE_DEFAULT[8], CUMULATIVE_DATE_DEFAULT[12], false)?" OK":" NOT OK"+"\n") );
        System.out.println("numberOfWeeks:" + (testNumberOfWeeks( new Date[] {CUMULATIVE_DATE_DEFAULT[5],CUMULATIVE_DATE_DEFAULT[18]} , 2)?" OK":" NOT OK"+"\n") );
        System.out.println("findCompleteMonth:" + (testFindCompleteMonth(CUMULATIVE_DATE_DEFAULT[0], CUMULATIVE_DATE_DEFAULT[CUMULATIVE_DATA_DEFAULT.length-1], new Date[] {CUMULATIVE_DATE_DEFAULT[1],CUMULATIVE_DATE_DEFAULT[CUMULATIVE_DATE_DEFAULT.length-1]})?" OK":" NOT OK"+"\n") );
        System.out.println("numberOfMonths:" + (testNumberOfMonths(new Date[]{CUMULATIVE_DATE_DEFAULT[1],CUMULATIVE_DATE_DEFAULT[CUMULATIVE_DATE_DEFAULT.length-1]} , 3)?" OK":" NOT OK"+"\n") );
        System.out.println("variationIsPossibleInDayBefore1:" + (testVariationIsPossibleInDayBefore(0, CUMULATIVE_DATE_DEFAULT[0], CUMULATIVE_DATE_DEFAULT[8], CUMULATIVE_DATE_DEFAULT, CUMULATIVE_DATE_DEFAULT.length, false)?" OK":" NOT OK"+"\n") );
        System.out.println("variationIsPossibleInDayBefore2:" + (testVariationIsPossibleInDayBefore(0, CUMULATIVE_DATE_DEFAULT[1], CUMULATIVE_DATE_DEFAULT[8], CUMULATIVE_DATE_DEFAULT, CUMULATIVE_DATE_DEFAULT.length, true)?" OK":" NOT OK"+"\n") );
        System.out.println("variationIsPossibleInDayBefore3:" + (testVariationIsPossibleInDayBefore(1, CUMULATIVE_DATE_DEFAULT[5], CUMULATIVE_DATE_DEFAULT[12], CUMULATIVE_DATE_DEFAULT, CUMULATIVE_DATE_DEFAULT.length, false)?" OK":" NOT OK"+"\n") );
        System.out.println("variationIsPossibleInDayBefore4:" + (testVariationIsPossibleInDayBefore(1, CUMULATIVE_DATE_DEFAULT[12], CUMULATIVE_DATE_DEFAULT[25], CUMULATIVE_DATE_DEFAULT, CUMULATIVE_DATE_DEFAULT.length, true)?" OK":" NOT OK"+"\n") );
        System.out.println("variationIsPossibleInDayBefore5:" + (testVariationIsPossibleInDayBefore(2, CUMULATIVE_DATE_DEFAULT[1], CUMULATIVE_DATE_DEFAULT[15], CUMULATIVE_DATE_DEFAULT, CUMULATIVE_DATE_DEFAULT.length, false)?" OK":" NOT OK"+"\n") );
        System.out.println("variationIsPossibleInDayBefore6:" + (testVariationIsPossibleInDayBefore(2, CUMULATIVE_DATE_DEFAULT[32], CUMULATIVE_DATE_DEFAULT[CUMULATIVE_DATA_DEFAULT.length-1], CUMULATIVE_DATE_DEFAULT, CUMULATIVE_DATE_DEFAULT.length, true)?" OK":" NOT OK"+"\n") );
        System.out.println("variationIsPossibleInDayBefore7:" + (testVariationIsPossibleInDayBefore(3, CUMULATIVE_DATE_DEFAULT[12], CUMULATIVE_DATE_DEFAULT[CUMULATIVE_DATE_DEFAULT.length-1], CUMULATIVE_DATE_DEFAULT, CUMULATIVE_DATE_DEFAULT.length, false)?" OK":" NOT OK"+"\n") );
        System.out.println("validateResolution1:" + (testValidateResolution("0", true)?" OK":" NOT OK"+"\n") );
        System.out.println("validateResolution2:" + (testValidateResolution("1", true)?" OK":" NOT OK"+"\n") );
        System.out.println("validateResolution3:" + (testValidateResolution("2", true)?" OK":" NOT OK"+"\n") );
        System.out.println("validateResolution4:" + (testValidateResolution("3", false)?" OK":" NOT OK"+"\n") );
        System.out.println("numberOfDays:" + (testNumberOfDays(CUMULATIVE_DATE_DEFAULT[0], CUMULATIVE_DATE_DEFAULT[4], 5)?" OK":" NOT OK"+"\n") );
        System.out.println("calculateIndex:" + (testCalculateIndex(CUMULATIVE_DATE_DEFAULT, CUMULATIVE_DATE_DEFAULT[5], CUMULATIVE_DATE_DEFAULT.length, 5)?" OK":" NOT OK"+"\n") );
        System.out.println("addDays: " + ((testAddDays(DEFAULT_DATE,1,DEFAULT_DATE_ADD_DAYS_EXPECTED))?"OK":"NOT OK"+"\n"));
        System.out.println("addMonth: " + ((testAddMonth(DEFAULT_DATE,1,DEFAULT_DATE_ADD_MONTHS_EXPECTED))?"OK":"NOT OK"+"\n"));
        System.out.println("copyArrayContent: " + ((testCopyArrayContent(CUMULATIVE_DATA_DEFAULT,CUMULATIVE_DATE_DEFAULT, DEFAULT_DATE_COPY_CONTENT_INITIAL,DEFAULT_DATE_COPY_CONTENT_FINAL,CUMULATIVE_DATE_DEFAULT_LENGTH,DIARIO_NAO_INFETADO_INDEX-1,EXPECTED_COPY_CONTENT_ARRAY)?"OK":"NOT OK"+"\n")));
        System.out.println("calculateAverage: " + ((testCalculateAverage(EXPECTED_COPY_CONTENT_ARRAY,EXPECTED_AVERAGE, true))?"OK":"NOT OK"));
        System.out.println("differenceVector: " + ((testDifferenceVector(EXPECTED_COPY_CONTENT_ARRAY,EXPECTED_COPY_CONTENT_ARRAY,EXPECTED_DIFFERENCE_VECTOR_ARRAY))?"OK":"NOT OK"+"\n"));
        System.out.println("calculateStandardDeviation: " + ((testCalculateStandardDeviation(EXPECTED_AVERAGE,EXPECTED_COPY_CONTENT_ARRAY,EXPECTED_STANDARD_DEVIATION, true))?"OK":"NOT OK"+"\n"));
        System.out.println("isValidDate1: " + ((testIsValidDate(DATE_PATTERN_IS_VALID_DATE1,1))?"OK":"NOT OK"+"\n"));
        System.out.println("isValidDate2: " + ((testIsValidDate(DATE_PATTERN_IS_VALID_DATE2,2))?"OK":"NOT OK"+"\n"));
        System.out.println("isValidDate3: " + ((testIsValidDate(DATE_PATTERN_IS_VALID_DATE3,0))?"OK":"NOT OK"+"\n"));
        System.out.println("validateInputFile1: " + ((testValidateInputFile(FILE_NAME1,true))?"OK":"NOT OK"+"\n"));
        System.out.println("validateInputFile2: " + ((testValidateInputFile(FILE_NAME2,false))?"OK":"NOT OK"+"\n"));
        System.out.println("getAllTotalDataArrayRequiredValues: " + ((testGetAllTotalDataArrayRequiredValues(DEFAULT_INTERVAL_DATE, TOTAL_DATE_DEFAULT, TOTAL_DATA_DEFAULT, TOTAL_DATA_DEFAULT.length, EXPECTED_VALUES_GET_ALL_TOTAL_VALUES))?"OK":"NOT OK"+"\n"));
        System.out.println("subtractMatrices: " + ((testSubtractMatrices(DEFAULT_MATRIX_TEST,DEFAULT_MATRIX_TEST,EXPECTED_MATRIX_TEST))?"OK":"NOT OK"+"\n"));
        System.out.println("fillArray: " + ((testFillArray(CUMULATIVE_DATE_DEFAULT,CUMULATIVE_DATA_DEFAULT, FILE_NAME1, "1",CUMULATIVE_DATE_DEFAULT_LENGTH))?"OK":"NOT OK"+"\n"));
        System.out.println("dailyFluctuation: " +(testDailyFluctuation(new Date(121,9,1), new Date(121,9,2),1, new int[] {696, 690})?"OK":"NOT OK"));
        System.out.println("weeklyFluctuation: " +(testWeeklyFluctuation(new Date(121,9,4), new Date(121,9,10), 1, new int[] {4198}, true)?"OK":"NOT OK"));
        System.out.println("monthlyFluctuation: " +(testMonthlyFluctuation(new Date(121,10,1), new Date(121,11,1), 1, new int[] {56598}, true)?"OK":"NOT OK"));
        System.out.println("findCompleteWeeks: " +(testFindCompleteWeeks(new Date(121,9,3), new Date(121,9,15), new Date[] { new Date(121,9,4), new Date(121,9,10)})?"OK":"NOT OK"));
        System.out.println("monthIsComplete: " +(testMonthIsComplete(new Date(121,10,1), new Date(121,11,1), true)?"OK":"NOT OK"));
        System.out.println("DateRangeIsInArray: " +(testDateRangeIsInArray((new Date(121,9,1)), new Date(121,9,10), true, CUMULATIVE_DATE_DEFAULT)?"OK":"NOT OK"));
        System.out.println("validateDates: " +(testValidateDates(new Date(121,9,1), new Date(121,9,10), true)?"OK":"NOT OK"));
        System.out.println("variationIsPossible: " +(testVariationIsPossible(new Date(121,9,4), new Date(121,9,10), true, 1)?"OK":"NOT OK"));
        System.out.println("calculateAllVariations: " +(testCalculateAllVariations(new Date(121,9,4), new Date(121,9,10), 1, new int[][] {{-17960, 4198, 2386, 409, 48}}, true)?"OK":"NOT OK"));
        System.out.println("daysToDeathMatrixN: " + (testDaysToDeathMatrixN(new double[][]{{0.999500, 0.030000, 0.002000, 0.001000}, {0.000500, 0.960000, 0.004000, 0.015000}, {0.000000, 0.007000, 0.980000, 0.020000}, {0.000000, 0.003000, 0.010000, 0.950000}}, MATRIX_N) ? "OK" : "NOT OK"));
        System.out.println("daysToDeathMatrixM: " + (testDaysToDeathMatrixM(MATRIX_N, MATRIX_M) ? "OK" : "NOT OK"));
        System.out.println("powerMatrix: "+(testPowerMatrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}}, 3, new double[][]{{468, 576, 684},{1062, 1305, 1548},{1656, 2034, 2412}})?"OK":"NOT OK"+"\n"));
        System.out.println("multiplyMatrices: "+(testMultiplyMatrices(new double[][]{{1,2,3},{4,5,6},{7,8,9}}, new double[][] {{9,8,7},{6,5,4},{3,2,1}}, new double[][]{{30, 24, 18},{84, 69, 54},{138, 114, 90}})?"OK":"NOT OK"+"\n"));
        System.out.println("predictDataInDay: "+(testPredictDataInDay(new Date(130,10,1), new double[][]{{8046039.123115211}, {113533.88917192772}, {58432.4352636327}, {18520.69252083088}, {1918914.8599301316}})?"OK":"NOT OK"));
        System.out.println("historicData: "+((testHistoricData(0, new double[][]{{10102947},{60026},{2122},{284},{37}}))?"OK":"NOT OK"+"\n"));
        System.out.println("removeDeaths: "+((testRemoveDeaths(TRANSITION_MATRIX_DEFAULT, new double[][]{{0.999500,0.030000,0.002000,0.001000},{0.000500,0.960000,0.004000,0.015000},{0.000000,0.007000,0.980000,0.020000},{0.000000,0.003000,0.010000,0.950000}}))?"OK":"NOT OK"+"\n"));
        System.out.println("luDecomposition: "+((testLuDecomposition(new double[][]{{1,-2,3},{0,-1,4},{-2,2,0}}, new double[][]{{1,-2,3},{0,1,-4},{0,0,1}}, new double[][]{{1,0,0},{0,-1,0},{-2,-2,-2}}))?"OK":"NOT OK"+"\n"));
        System.out.println("invertLower: "+((testInvertLower(new double[][]{{1,0,0},{0,-1,0},{-2,-2,-2}}, new double[][]{{1,0,0},{0,-1,0},{-1,1,-0.5}}))?"OK":"NOT OK"+"\n"));
        System.out.println("invertUpper: "+((testInvertUpper(new double[][]{{1,-2,3},{0,1,-4},{0,0,1}}, new double[][]{{1,2,5},{0,1,4},{0,0,1}}))?"OK":"NOT OK"+"\n"));
        System.out.println("invertMatrix: "+((testInvertMatrix(new double[][]{{1,1,0},{2,1,1},{3,1,1}}, new double[][]{{0,-1,1},{1,1,-1},{-1,2,-1}}))?"OK":"NOT OK"+"\n"));
        System.out.println("readDayNIM: "+((testReadDayNIM("01-11-2020",new Date(120,10,1)))?"OK":"NOT OK"+"\n"));
        System.out.println("sameSizeIntervals: "+((testSameSizeIntervals(new Date[]{new Date(120,10,1), new Date(120,10,10)}, new Date[]{new Date(120,10,15), new Date(120,10,20)}, new Date[]{new Date(120,10,1), new Date(120,10,6)}, new Date[]{new Date(120,10,15), new Date(120,10,20)})?"OK":"NOT OK"+"\n")));
        System.out.println("DaysInString:" + (testDaysInString( new Date(120, 10, 1),5,new String[]{"01-11-2020", "02-11-2020", "03-11-2020", "04-11-2020", "05-11-2020"}) ? "OK" : "NOT OK"));
        System.out.println("WeekIntervalsInString:" + (testWeekIntervalsInString( new Date(120, 10, 1),new Date(120, 10, 15),new String[][]{{"02-11-2020", "08-11-2020"}, {"09-11-2020", "15-11-2020"}}) ? "OK" : "NOT OK"));
        System.out.println("MonthInString:" + (testMonthInString(new Date(120, 10, 1),1,new String[]{"11-2020"}) ? "OK" : "NOT OK"));
        System.out.println("DuplicateMatrix:" + (testDuplicateMatrix(TRANSITION_MATRIX_DEFAULT) ? "OK" : "NOT OK"));
        System.out.println("CheckIfNull1:" + (testCheckIfNull1(false) ? "OK" : "NOT OK"));
        System.out.println("CheckIfNull2:" + (testCheckIfNull2(false,TRANSITION_MATRIX_DEFAULT) ? "OK" : "NOT OK"));
        System.out.println("DateIsInArray:" + (testDateIsInArray(false,new Date(120, 10, 1)) ? "OK" : "NOT OK"));
        System.out.println("correctFileName1:" + (testCorrectFileName(FILE_NAME2, 0, FILE_NAME2)? "OK" : "NOT OK"));
        System.out.println("correctFileName2:" + (testCorrectFileName(FILE_NAME2_TXT, 1, FILE_NAME2_TXT)? "OK" : "NOT OK"));
        System.out.println("correctFileName3:" + (testCorrectFileName(FILE_NAME2_WITHOUT_EXT, 1, FILE_NAME2_TXT)? "OK" : "NOT OK"));
        System.out.println("correctFileName4:" + (testCorrectFileName(FILE_NAME2_WITHOUT_EXT, 0, FILE_NAME2)? "OK" : "NOT OK"));
    }

}