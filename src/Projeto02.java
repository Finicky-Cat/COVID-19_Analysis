import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Projeto02 {
    private static final Scanner in = new Scanner(System.in);
    private static final int DATA_INDEX = 0;
    private static final int DIARIO_NAO_INFETADO_INDEX = 1;
    private static final int ACUMULADO_INFETADO_INDEX = 2;
    private static final int ACUMULADO_HOSPITALIZADO_INDEX = 3;
    private static final int ACUMULADO_INTERNADO_UCI_INDEX = 4;
    private static final int ACUMULADO_MORTES_INDEX = 5;
    private static final int COLUMN_LENGTH_DATA_ARRAY = 5;
    private static final int MAX_ARRAY_LENGTH = 1000;
    private static final String[] NAMES_TOTAL_CSV = {"nao_infetados", "infetados", "hospitalizados", "internados_UCI", "obitos"};
    private static final String[] NAMES_CSV = {"nao_infetados", "novos_casos", "novas_hospitalizacoes", "novos_internados_UCI", "novas_mortes"};
    private static final String[] NAMES = {"Não infetados", "Novos casos", "Novas hospit.", "Novos intern. UCI", "Novas mortes"};
    private static final String[] NAMES_TOTAL = {"Não infetados", "Infetados", "Hospitalizados", "Internados UCI", "Óbitos"};

    public static void main(String[] args) throws IOException, ParseException {

        int arrayLengthTotal = 0;
        int arrayLengthCumulative = 0;
        Date[] dateArrayTotal = new Date[MAX_ARRAY_LENGTH];
        int[][] dataArrayTotal = new int[MAX_ARRAY_LENGTH][COLUMN_LENGTH_DATA_ARRAY];
        Date[] dateArrayCumulative = new Date[MAX_ARRAY_LENGTH];
        int[][] dataArrayCumulative = new int[MAX_ARRAY_LENGTH][COLUMN_LENGTH_DATA_ARRAY];
        double[][] matrixData = new double[COLUMN_LENGTH_DATA_ARRAY][COLUMN_LENGTH_DATA_ARRAY];
        boolean closeApp = false;
        if (args.length > 0) {
            notInteractiveMode(args, dateArrayCumulative, dataArrayCumulative, dateArrayTotal,dataArrayTotal, matrixData);
        } else {
            while (!closeApp) {
                System.out.println("\n\n-------------------------------");
                System.out.println("   Bem-vindo(a) à app da MSP   ");
                System.out.println("-------------------------------");
                System.out.println("--------MENU PRINCIPAL---------");
                System.out.println("\n\nOpção 1: Menu seleção de ficheiros");
                System.out.println("Opção 2: Menu Funcionalidades");
                System.out.println("Opção 0: Sair da App");
                System.out.println("Selecione o número correspondente á sua opção:");
                String mainOption = in.next();
                switch (mainOption) {
                    case "1":
                        System.out.println("----------Menu de inserção de ficheiros-----------");
                        boolean goback = false;
                        while (!goback) {
                            System.out.println("\nIntroduza o tipo de ficheiro que pretende carregar");
                            System.out.println("Opção 1: Acumulado");
                            System.out.println("Opção 2: Total");
                            System.out.println("Opção 3: Matriz");
                            System.out.println("Opção 0: Retornar");
                            System.out.println("\nIntroduza o número correspondente à sua opção:");
                            String fileOption = in.next();


                            switch (fileOption) {
                                case "1":
                                    arrayLengthCumulative = fileVerification(dateArrayCumulative, dataArrayCumulative, "1");
                                    break;

                                case "2":
                                    arrayLengthTotal = fileVerification(dateArrayTotal, dataArrayTotal, "2");
                                    break;

                                case "3":
                                    fileVerification(matrixData);
                                    break;


                                case "0":
                                    goback = true;
                                    System.out.println("-----Voltando ao menu anterior-----");
                                    break;
                                default:
                                    System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                    break;

                            }
                        }
                        break;
                    case "2":
                        if (!checkIfNull(dateArrayCumulative) || !checkIfNull(dateArrayTotal)) {
                            boolean returnToMainMenu = false;
                            while (!returnToMainMenu) {

                                System.out.println("\n-----O que deseja visualizar?-----");
                                System.out.println("\nOpção 1: Variações");
                                System.out.println("Opção 2: Comparações");
                                System.out.println("Opção 3: Totais");
                                System.out.println("Opção 4: Previsões");
                                System.out.println("Opção 0: Retornar ao Menu Principal");
                                System.out.println("Selecione o número da opção que deseja:");

                                String visualizationOption = in.next();


                                switch (visualizationOption) {
                                    case "1":
                                        if (!checkIfNull(dateArrayCumulative)) {

                                            boolean exit = false;

                                            while (!exit) {
                                                chooseNumberType();
                                                System.out.println("Opção 6: Todos");
                                                System.out.println("Opção 0: Retornar");
                                                System.out.println("\nSelecione o número correspondente à sua opção:");
                                                String option = in.next();

                                                boolean exit2;
                                                switch (option) {
                                                    case "1", "2", "3", "4", "5":
                                                        exit2 = false;
                                                        while (!exit2) {
                                                            Date date1 = new Date();
                                                            Date date2 = new Date();
                                                            boolean exit3 = false;
                                                            while (!exit3) {
                                                                subMenu();
                                                                System.out.println("\nIntroduza o número que corresponde à resolução que pretende observar:");
                                                                String resolution = in.next();
                                                                switch (resolution) {
                                                                    case "0", "1", "2":
                                                                        System.out.println("\n\n----------------Insira as datas do intervalo desejado---------------");
                                                                        Date[] dates = readDatesMenu(dateArrayCumulative, arrayLengthCumulative);
                                                                        date1 = dates[0];
                                                                        date2 = dates[1];
                                                                        if (variationIsPossible(Integer.parseInt(resolution), date1, date2)) {
                                                                            boolean dayBefore = variationIsPossibleInDayBefore(Integer.parseInt(resolution), date1, date2, dateArrayCumulative, arrayLengthCumulative);
                                                                            int[] requestedDataArray = null;
                                                                            switch (resolution) {
                                                                                case "0":
                                                                                    requestedDataArray = dailyFluctuation(dataArrayCumulative, dateArrayCumulative, date1, date2, arrayLengthCumulative, Integer.parseInt(option) - 1, dayBefore);
                                                                                    break;
                                                                                case "1":
                                                                                    requestedDataArray = weeklyFluctuation(dataArrayCumulative, dateArrayCumulative, date1, date2, Integer.parseInt(option) - 1, arrayLengthCumulative, dayBefore);
                                                                                    break;
                                                                                case "2":
                                                                                    requestedDataArray = monthlyFluctuation(dataArrayCumulative, dateArrayCumulative, date1, date2, Integer.parseInt(option) - 1, arrayLengthCumulative, dayBefore);
                                                                                    break;
                                                                            }
                                                                            printResult(requestedDataArray, Integer.parseInt(resolution), date1, date2, Integer.parseInt(option) - 1, dayBefore);
                                                                            saveCSVMenu(Integer.parseInt(resolution), requestedDataArray, date1, date2, Integer.parseInt(option) - 1, Integer.parseInt(visualizationOption));
                                                                            exit3 = true;
                                                                        } else {
                                                                            System.out.println("ERRO: A resolução selecionada é inválida para o número de dias do intervalo introduzido!");
                                                                        }
                                                                        break;
                                                                    case "3":
                                                                        exit2 = true;
                                                                        exit3 = true;
                                                                        System.out.println("-----Retornando ao menu anterior-----");
                                                                        break;
                                                                    default:
                                                                        System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                                                        break;
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case "6":
                                                        exit2 = false;
                                                        while (!exit2) {
                                                            Date date1 = new Date();
                                                            Date date2 = new Date();
                                                            boolean exit3 = false;
                                                            while (!exit3) {
                                                                subMenu();
                                                                System.out.println("\nIntroduza o número que corresponde à resolução que pretende observar:");
                                                                String resolution = in.next();
                                                                switch (resolution) {
                                                                    case "0", "1", "2":
                                                                        System.out.println("\n\n----------------Insira as datas do intervalo desejado---------------");
                                                                        Date[] dates = readDatesMenu(dateArrayCumulative, arrayLengthCumulative);
                                                                        date1 = dates[0];
                                                                        date2 = dates[1];
                                                                        if (variationIsPossible(Integer.parseInt(resolution), date1, date2)) {
                                                                            boolean dayBefore = variationIsPossibleInDayBefore(Integer.parseInt(resolution), date1, date2, dateArrayCumulative, arrayLengthCumulative);
                                                                            int[][] requestedDataArray = calculateAllVariations(Integer.parseInt(resolution), dateArrayCumulative, dataArrayCumulative, date1, date2, arrayLengthCumulative, dayBefore);
                                                                            printResult(requestedDataArray, Integer.parseInt(resolution), date1, date2, dayBefore);
                                                                            saveCSVMenu(Integer.parseInt(resolution), requestedDataArray, date1, date2, Integer.parseInt(visualizationOption));
                                                                            exit3 = true;
                                                                        } else {
                                                                            System.out.println("ERRO: A resolução selecionada é inválida para o número de dias do intervalo introduzido!");
                                                                        }
                                                                        break;
                                                                    case "3":
                                                                        exit2 = true;
                                                                        exit3 = true;
                                                                        System.out.println("-----Retornando ao menu anterior-----");
                                                                        break;
                                                                    default:
                                                                        System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                                                        break;
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case "0":
                                                        exit = true;
                                                        System.out.println("-----Retornando ao menu anterior-----");
                                                        break;
                                                    default:
                                                        System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                                        break;
                                                }

                                            }
                                        } else {
                                            System.out.println("ERRO: Não existem dados para executar esta funcionalidade (Dados Acumulados em falta)!");
                                        }

                                        break;
                                    case "2":
                                        if (!checkIfNull(dateArrayCumulative) || !checkIfNull(dateArrayTotal)) {
                                            boolean calcTypeExist = false;
                                            while (!calcTypeExist) {
                                                System.out.println("\n--Insira o número corresponde à operação que pretende realizar--");
                                                System.out.println("\nOpção 1: Utilizar valores acumulados");
                                                System.out.println("Opção 2: Utilizar valores totais");
                                                System.out.println("Opção 0: Retornar");
                                                System.out.println("Introduza a sua escolha:");
                                                String calcType = in.next();
                                                Date[] getDates = new Date[4];
                                                String type;
                                                switch (calcType) {
                                                    case "0":
                                                        calcTypeExist = true;
                                                        System.out.println("-----Retornando ao menu anterior-----");
                                                        break;
                                                    case "1":
                                                        if (!checkIfNull(dateArrayCumulative)) {
                                                            boolean calcTypeExist1 = false;
                                                            while (!calcTypeExist1) {
                                                                chooseNumberType();
                                                                System.out.println("Opção 0: Retornar");
                                                                System.out.println("\nSelecione o número correspondente à sua opção:");
                                                                type = in.next();

                                                                switch (type) {
                                                                    case "1", "2", "3", "4", "5":
                                                                        getDates = askDates(dateArrayCumulative, arrayLengthCumulative);
                                                                        sameSizeIntervals(getDates);
                                                                        boolean dayBefore = variationIsPossibleInDayBefore(0, getDates[0], getDates[1], dateArrayCumulative, arrayLengthCumulative);
                                                                        int[] requestedDataArray1 = dailyFluctuation(dataArrayCumulative, dateArrayCumulative, getDates[0], getDates[1], arrayLengthCumulative, Integer.parseInt(type) - 1, dayBefore);
                                                                        int[] requestedDataArray2 = dailyFluctuation(dataArrayCumulative, dateArrayCumulative, getDates[2], getDates[3], arrayLengthCumulative, Integer.parseInt(type) - 1, dayBefore);
                                                                        int[] difference = differenceVector(requestedDataArray1, requestedDataArray2);
                                                                        double average1 = calculateAverage(requestedDataArray1, dayBefore);
                                                                        double average2 = calculateAverage(requestedDataArray2, dayBefore);
                                                                        double sd1 = calculateStandardDeviation(average1, requestedDataArray1, dayBefore);
                                                                        double sd2 = calculateStandardDeviation(average2, requestedDataArray2, dayBefore);
                                                                        printResult(requestedDataArray1, requestedDataArray2, difference, average1, average2, sd1, sd2, daysInString(getDates[0], requestedDataArray1.length), daysInString(getDates[2], requestedDataArray2.length), dayBefore);
                                                                        saveCSVMenuComparison(requestedDataArray1, requestedDataArray2, difference, average1, average2, sd1, sd2, getDates);
                                                                        break;
                                                                    case "0":
                                                                        calcTypeExist1 = true;
                                                                        System.out.println("-----Retornando ao menu anterior-----");
                                                                        break;
                                                                    default:
                                                                        System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                                                        break;
                                                                }
                                                            }
                                                        } else {
                                                            System.out.println("ERRO: Não existem dados para executar esta funcionalidade (Dados Acumulados em falta)!");
                                                        }
                                                        break;
                                                    case "2":
                                                        if (!checkIfNull(dateArrayTotal)) {
                                                            boolean calcExist = false;
                                                            while (!calcExist) {
                                                                chooseNumberType();
                                                                System.out.println("Opção 0: Retornar");
                                                                System.out.println("\nSelecione o número correspondente à sua opção:");
                                                                String type1 = in.next();

                                                                switch (type1) {
                                                                    case "1", "2", "3", "4", "5":
                                                                        getDates = askDates(dateArrayTotal, arrayLengthTotal);
                                                                        sameSizeIntervals(getDates);
                                                                        int[] requestedDataArray1 = copyArrayContent(dataArrayTotal, dateArrayTotal, getDates[0], getDates[1], arrayLengthTotal, Integer.parseInt(type1) - 1);
                                                                        int[] requestedDataArray2 = copyArrayContent(dataArrayTotal, dateArrayTotal, getDates[2], getDates[3], arrayLengthTotal, Integer.parseInt(type1) - 1);
                                                                        int[] difference = differenceVector(requestedDataArray1, requestedDataArray2);
                                                                        double average1 = calculateAverage(requestedDataArray1, true);
                                                                        double average2 = calculateAverage(requestedDataArray2, true);
                                                                        double sd1 = calculateStandardDeviation(average1, requestedDataArray1, true);
                                                                        double sd2 = calculateStandardDeviation(average2, requestedDataArray2, true);
                                                                        printResult(requestedDataArray1, requestedDataArray2, difference, average1, average2, sd1, sd2, daysInString(getDates[0], requestedDataArray1.length), daysInString(getDates[2], requestedDataArray2.length), true);
                                                                        saveCSVMenuComparison(requestedDataArray1, requestedDataArray2, difference, average1, average2, sd1, sd2, getDates);
                                                                        break;
                                                                    case "0":
                                                                        calcExist = true;
                                                                        System.out.println("-----Retornando ao menu anterior-----");
                                                                        break;
                                                                    default:
                                                                        System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                                                        break;
                                                                }
                                                            }

                                                        } else {
                                                            System.out.println("ERRO: Não existem dados para executar esta funcionalidade (Dados Totais em falta)!");
                                                        }
                                                        break;
                                                }
                                            }
                                        } else {
                                            System.out.println("ERRO: Não existem dados para executar esta funcionalidade (Dados Acumulados e Totais em falta)!");
                                        }
                                        break;
                                    case "3":
                                        if (!checkIfNull(dateArrayTotal)) {
                                            boolean calcExist2 = false;
                                            while (!calcExist2) {
                                                chooseNumberType();
                                                System.out.println("Opção 6: Todos");
                                                System.out.println("Opção 0: Retornar");
                                                System.out.println("\nSelecione o número correspondente à sua opção:");
                                                String option = in.next();
                                                switch (option) {
                                                    case "1", "2", "3", "4", "5":
                                                        System.out.println("\n\n----------------Insira as datas do intervalo desejado---------------");
                                                        Date[] dates = readDatesMenu(dateArrayTotal, arrayLengthTotal);
                                                        int[] requestDataArray = copyArrayContent(dataArrayTotal, dateArrayTotal, dates[0], dates[1], arrayLengthTotal, Integer.parseInt(option) - 1);
                                                        printResultTotals(requestDataArray, dates, Integer.parseInt(option) - 1);
                                                        saveCSVMenu(0, requestDataArray, dates[0], dates[1], Integer.parseInt(option) - 1, Integer.parseInt(visualizationOption));
                                                        break;
                                                    case "6":
                                                        System.out.println("\n\n----------------Insira as datas do intervalo desejado---------------");
                                                        dates = readDatesMenu(dateArrayTotal, arrayLengthTotal);
                                                        int[][] requestedDataArray = getAllTotalArrayRequiredValues(dates, dateArrayTotal, dataArrayTotal, arrayLengthTotal);
                                                        printResultTotals(requestedDataArray, dates);
                                                        saveCSVMenu(0, requestedDataArray, dates[0], dates[1],  Integer.parseInt(visualizationOption));
                                                        break;
                                                    case "0":
                                                        calcExist2 = true;
                                                        System.out.println("-----Retornando ao menu anterior-----");
                                                        break;
                                                    default:
                                                        System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                                        break;
                                                }
                                            }

                                        } else {
                                            System.out.println("ERRO: Não existem dados para executar esta funcionalidade (Dados Totais em falta)!");
                                        }
                                        break;
                                    case "4":
                                        if (!checkIfNull(dateArrayTotal) && !checkIfNull(matrixData)) {
                                            boolean exitPrediction = false;
                                            while (!exitPrediction) {
                                                System.out.println("\n\nPor favor, escolha a estatística que deseja ver:");
                                                System.out.println("Opção 1: Previsão do número de casos ");
                                                System.out.println("Opção 2: Previsão do número de dias até ao óbito");
                                                System.out.println("Opção 0: Retornar");
                                                System.out.println("\nSelecione o número correspondente à sua opção:");
                                                String optionPrediction = in.next();
                                                switch (optionPrediction) {
                                                    case "1":
                                                        boolean exitPrediction2 = false;
                                                        while (!exitPrediction2) {
                                                            chooseNumberType();
                                                            System.out.println("Opção 6: Todos");
                                                            System.out.println("Opção 0: Retornar");
                                                            System.out.println("\nSelecione o número correspondente à sua opção:");
                                                            String calcType = in.next();
                                                            Date datePrediction = null;
                                                            switch (calcType) {
                                                                case "1", "2", "3", "4", "5", "6":
                                                                    System.out.println("\nIntroduza a data da previsão que deseja vizualizar (dd-MM-aaaa ou aaaa-MM-dd):");
                                                                    datePrediction = readPredictionDay(dateArrayTotal);
                                                                    double[][] dataPrediction = predictDataInDay(datePrediction, dateArrayTotal, arrayLengthTotal, matrixData, dataArrayTotal);
                                                                    printPredictions(Integer.parseInt(calcType) - 1, dataPrediction, datePrediction);
                                                                    break;
                                                                case "0":
                                                                    exitPrediction2 = true;
                                                                    System.out.println("-----Retornando ao menu anterior-----");
                                                                    break;
                                                                default:
                                                                    System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    case "2":
                                                        boolean exitPrediction3 = false;
                                                        String[] transientStates = {"Não infetado", "Infetado", "Hospitalizado", "Internado na UCI"};
                                                        while(!exitPrediction3) {
                                                            System.out.println("\n\nPor favor, escolha o estado transiente do indivíduo:");
                                                            for (int i = 0; i < transientStates.length; i++)
                                                                System.out.printf("Opção %d: %s\n", i+1, transientStates[i]);
                                                            System.out.println("Opção 5: Todos");
                                                            System.out.println("Opção 0: Retornar");
                                                            System.out.println("\nSelecione o número correspondente à sua opção:");
                                                            String calcType2 = in.next();
                                                            switch (calcType2) {
                                                                case "1", "2", "3", "4":
                                                                    System.out.printf("\nEstado Transiente: %s\n\n", transientStates[Integer.parseInt(calcType2)-1]);
                                                                    System.out.printf("Dias até ao óbito: %.1f\n", daysToDeathMatrixM(daysToDeathMatrixN(removeDeaths(matrixData)))[0][Integer.parseInt(calcType2)-1]);
                                                                    break;
                                                                case "5":
                                                                    for (int i = 0; i < transientStates.length; i++)
                                                                        System.out.printf("%-20s", transientStates[i]);
                                                                    System.out.println();
                                                                    double[][] daysTillDeath = daysToDeathMatrixM(daysToDeathMatrixN(removeDeaths(matrixData)));
                                                                    for (int i = 0; i < daysTillDeath[0].length; i++)
                                                                        System.out.printf("%-20.1f", daysTillDeath[0][i]);
                                                                    System.out.println();
                                                                    break;
                                                                case "0":
                                                                    exitPrediction3 = true;
                                                                    System.out.println("-----Retornando ao menu anterior-----");
                                                                    break;
                                                                default:
                                                                    System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                                                    break;

                                                            }
                                                        }
                                                        break;
                                                    case "0":
                                                        exitPrediction = true;
                                                        System.out.println("-----Retornando ao menu principal-----");
                                                        break;
                                                    default:
                                                        System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                                        break;

                                                }
                                            }
                                        }else{
                                            System.out.println("ERRO: Não existem dados para executar esta funcionalidade (Dados Totais e/ou da Matriz em falta)!");
                                        }
                                        break;
                                    case "0":
                                        returnToMainMenu = true;
                                        System.out.println("-----Retornando ao menu principal-----");
                                        break;

                                    default:
                                        System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("ERRO: Não existem dados para executar esta funcionalidade!");
                        }
                        break;
                    case "0":
                        closeApp = true;
                        System.out.println("Obrigado por usar a nossa App!");
                        break;

                    default:
                        System.out.println("Ocorreu um erro. A opção selecionada é inválida!");
                        break;
                }
            }
        }

    }

    public static void chooseNumberType() {
        System.out.println("\n\nPor favor, escolha a estatística que deseja ver:");
        System.out.println("Opção 1: Número de não infetados ");
        System.out.println("Opção 2: Número de infetados");
        System.out.println("Opção 3: Número de hospitalizados");
        System.out.println("Opção 4: Número de internados na UCI");
        System.out.println("Opção 5: Número de mortes");
    }

    public static Date[] askDates(Date[] dateArray, int arraysLength) throws ParseException {
        Date date1 = null, date2 = null, date3 = null, date4 = null;
        boolean verification = false;
        while (!verification) {
            System.out.println("\n----------------Insira as datas do primeiro intervalo a comparar---------------");
            Date[] dates = readDatesMenu(dateArray, arraysLength);
            date1 = dates[0];
            date2 = dates[1];
            System.out.println("\n----------------Insira as datas do segundo intervalo a comparar---------------");
            Date[] dates2 = readDatesMenu(dateArray, arraysLength);
            date3 = dates2[0];
            date4 = dates2[1];
            if (!date1.equals(date3) && !date2.equals(date4)) {
                verification = true;
            } else {
                System.out.println("\nERRO: Os intervalos introduzidos são iguais!");
            }
        }
        return new Date[]{date1, date2, date3, date4};
    }

    public static void printResultTotals(int[][] dataArray, Date[] dates) {
        Date initialDate = dates[0], finalDate = dates[1];
        String[] datesRange = daysInString(initialDate, numberOfDays(initialDate, finalDate));
        System.out.printf("Registo diário de total de casos de %s a %s\n\n", datesRange[0], datesRange[datesRange.length - 1]);
        System.out.printf("%-25s", "Intervalo de tempo");
        for (int line = 0; line < dataArray[0].length; line++)
            System.out.printf("%20s", NAMES_TOTAL[line]);
        System.out.printf("\n");
        for (int line = 0; line < dataArray.length; line++) {
            System.out.printf("%-23s ", datesRange[line] + ":");
            for (int column = 0; column < dataArray[0].length; column++) {
                System.out.printf("%20s", dataArray[line][column]);
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static void printResultTotals(int[] dataArray, Date[] dates, int option) {
        Date initialDate = dates[0], finalDate = dates[1];
        String[] datesRange = daysInString(initialDate, numberOfDays(initialDate, finalDate));
        System.out.printf("Registo diário de total de casos de %s a %s\n\n", datesRange[0], datesRange[datesRange.length - 1]);
        System.out.printf("%-25s", "Intervalo de tempo");
        System.out.printf("%20s\n", NAMES_TOTAL[option]);
        for (int line = 0; line < dataArray.length; line++) {
            System.out.printf("%-23s ", datesRange[line] + ":");
            System.out.printf("%20s\n", dataArray[line]);
        }
        System.out.println("\n");
    }

    public static void printResult(int[] requestedDataArray, int resolution, Date initialDate, Date finalDate, int type, boolean dayBefore) {
        String[] datesRange = null;
        String[][] weeklyDateRange = null;
        Date[] dateRange = null;
        boolean error = false;
        switch (resolution) {
            case 0:
                datesRange = daysInString(initialDate, numberOfDays(initialDate, finalDate));
                System.out.printf("\nRegisto diário de %s a %s\n\n", datesRange[0], datesRange[datesRange.length - 1]);
                break;
            case 1:
                dateRange = findCompleteWeeks(initialDate, finalDate);
                weeklyDateRange = weekIntervalsInString(dateRange[0], dateRange[1]);
                System.out.printf("\nRegisto semanal de %s a %s\n\n", weeklyDateRange[0][0], weeklyDateRange[weeklyDateRange.length - 1][1]);
                break;
            case 2:
                dateRange = findCompleteMonth(initialDate, finalDate);
                datesRange = monthsInString(dateRange[0], numberOfMonths(new Date[]{dateRange[0], dateRange[1]}));
                System.out.printf("\nRegisto mensal de %s a %s\n\n", datesRange[0], datesRange[datesRange.length - 1]);
                break;
            default:
                error = true;
                break;
        }
        if (!error) {
            System.out.printf("%-25s", "Intervalo de tempo");
            System.out.printf("%20s", NAMES[type]);
            System.out.println();
            if (!dayBefore) {
                switch (resolution) {
                    case 0, 2:
                        System.out.printf("%s: Erro: Dados insuficientes para calcular a variacao.\n", datesRange[0]);
                        break;
                    case 1:
                        System.out.printf("%s a %s: Dados insuficientes para calcular a variacao.\n", weeklyDateRange[0][0], weeklyDateRange[0][1]);
                        break;
                }
            } else {
                switch (resolution) {
                    case 0, 2:
                        System.out.printf("%-23s ", datesRange[0] + ":");
                        break;
                    case 1:
                        System.out.printf("%s a %s: ", weeklyDateRange[0][0], weeklyDateRange[0][1]);
                        break;
                }
                String sinal;
                if (requestedDataArray[0] >= 0)
                    sinal = "+";
                else
                    sinal = "";
                System.out.printf("%20s\n", sinal + requestedDataArray[0]);
            }
            for (int line = 1; line < requestedDataArray.length; line++) {
                switch (resolution) {
                    case 0, 2:
                        System.out.printf("%-23s ", datesRange[line] + ":");
                        break;
                    case 1:
                        System.out.printf("%s a %s: ", weeklyDateRange[line][0], weeklyDateRange[line][1]);
                        break;
                }
                String sinal;
                if (requestedDataArray[line] >= 0)
                    sinal = "+";
                else
                    sinal = "";
                System.out.printf("%20s\n", sinal + requestedDataArray[line]);
            }
        }
    }

    public static void printResult(int[] variationDataArrayInitial, int[] variationDataArrayFinal, int[] differenceVector,
                                   double average1, double average2, double sd1, double sd2, String[] daysInString, String[] daysInString2, boolean dayBefore) throws IOException {

        System.out.printf("\nComparação diária entre %s / %s e %s / %s\n\n", daysInString[0], daysInString[variationDataArrayInitial.length - 1], daysInString2[0], daysInString2[variationDataArrayFinal.length - 1]);
        System.out.printf("%14s |%14s |%10s |%10s |%10s\n", "Dia Período 1", "Dia Período 2", "Período 1", "Período 2", "Diferença");
        if (!dayBefore)
            System.out.printf("%14s |%14s | Erro: Dados insuficientes para calcular a variacao a %s.\n", daysInString[0], daysInString2[0], daysInString[0]);
        else
            System.out.printf("%14s |%14s |%10d |%10d |%10d\n", daysInString[0], daysInString2[0], variationDataArrayInitial[0], variationDataArrayFinal[0], differenceVector[0]);
        for (int i = 1; i < variationDataArrayInitial.length; i++) {
            System.out.printf("%14s |%14s |%10d |%10d |%10d\n", daysInString[i], daysInString2[i], variationDataArrayInitial[i], variationDataArrayFinal[i], differenceVector[i]);
        }
        System.out.printf("\nPeríodo %s a %s: Média: %.4f | Desvio Padrão: %.4f", daysInString[0], daysInString[variationDataArrayInitial.length - 1], average1, sd1);
        System.out.printf("\nPeríodo %s a %s: Média: %.4f | Desvio Padrão: %.4f\n", daysInString2[0], daysInString2[variationDataArrayFinal.length - 1], average2, sd2);
    }

    public static void printResult(int[][] requestedDataArray, int resolution, Date initialDate, Date finalDate, boolean dayBefore) {
        String[] datesRange = null;
        String[][] weeklyDateRange = null;
        Date[] dateRange = null;
        boolean error = false;
        switch (resolution) {
            case 0:
                datesRange = daysInString(initialDate, numberOfDays(initialDate, finalDate));
                System.out.printf("\nRegisto diário de %s a %s\n\n", datesRange[0], datesRange[datesRange.length - 1]);
                break;
            case 1:
                dateRange = findCompleteWeeks(initialDate, finalDate);
                weeklyDateRange = weekIntervalsInString(dateRange[0], dateRange[1]);
                System.out.printf("\nRegisto semanal de %s a %s\n\n", weeklyDateRange[0][0], weeklyDateRange[weeklyDateRange.length - 1][1]);
                break;
            case 2:
                dateRange = findCompleteMonth(initialDate, finalDate);
                datesRange = monthsInString(dateRange[0], numberOfMonths(new Date[]{dateRange[0], dateRange[1]}));
                System.out.printf("\nRegisto mensal de %s a %s\n\n", datesRange[0], datesRange[datesRange.length - 1]);
                break;
            default:
                error = true;
                break;
        }
        if (!error) {
            System.out.printf("%-25s", "Intervalo de tempo");
            for (int line = 0; line < requestedDataArray[0].length; line++)
                System.out.printf("%20s", NAMES[line]);
            System.out.println();
            if (!dayBefore) {
                switch (resolution) {
                    case 0, 2:
                        System.out.printf("%-23s Erro: Dados insuficientes para calcular a variacao.\n", datesRange[0] + ":");
                        break;
                    case 1:
                        System.out.printf("%s a %s: Dados insuficientes para calcular a variacao.\n", weeklyDateRange[0][0], weeklyDateRange[0][1]);
                        break;
                }
            } else {
                    switch (resolution) {
                        case 0, 2:
                            System.out.printf("%-23s ", datesRange[0] + ":");
                            break;
                        case 1:
                            System.out.printf("%s a %s: ", weeklyDateRange[0][0], weeklyDateRange[0][1]);
                            break;
                    }
                    for (int column = 0; column < requestedDataArray[0].length; column++) {
                        String sinal;
                        if (requestedDataArray[0][column] >= 0)
                            sinal = "+";
                        else
                            sinal = "";
                        System.out.printf("%20s", sinal + requestedDataArray[0][column]);
                    }
                    System.out.println();
            }
            for (int line = 1; line < requestedDataArray.length; line++) {
                switch (resolution) {
                    case 0, 2:
                        System.out.printf("%-23s ", datesRange[line] + ":");
                        break;
                    case 1:
                        System.out.printf("%s a %s: ", weeklyDateRange[line][0], weeklyDateRange[line][1]);
                        break;
                }
                for (int column = 0; column < requestedDataArray[0].length; column++) {
                    String sinal;
                    if (requestedDataArray[line][column] >= 0)
                        sinal = "+";
                    else
                        sinal = "";
                    System.out.printf("%20s", sinal + requestedDataArray[line][column]);
                }
                System.out.println();
            }
        }
    }

    public static void subMenu() {
        System.out.println("\nPor favor,selecione o tipo de comparação dos dados");
        System.out.println("Opção 0: Diária");
        System.out.println("Opção 1: Semanal");
        System.out.println("Opção 2: Mensal");
        System.out.println("Opção 3: Retornar");
    }

    public static Date[] readDatesMenu(Date[] dateArray, int arraysLength) throws ParseException {
        Date[] result = new Date[2];
        boolean error = true;
        Date date1 = null, date2 = null;
        while (error) {
            System.out.println("\nInsira a data inicial (dd-MM-aaaa ou aaaa-MM-dd):");
            date1 = readDay();
            System.out.println("\nInsira a data final (dd-MM-aaaa ou aaaa-MM-dd):");
            date2 = readDay();
            boolean validation1 = dateRangeIsInArray(dateArray, date1, date2, arraysLength);
            if (!validation1) {
                System.out.println("ERRO: Data(s) introduzidas não constam no ficheiro CSV lido!\n");
            }
            boolean validation2 = validateDates(date1, date2);
            if (validation1 && validation2) {
                error = false;
                result[0] = date1;
                result[1] = date2;
            }
        }
        return result;
    }

    public static String correctFileName(String fileName, int fileType) {
        String regexFileCSV = ".*\\.csv";
        String regexFileTXT = ".*\\.txt";
        Pattern patternCSV = Pattern.compile(regexFileCSV);
        Pattern patternTXT = Pattern.compile(regexFileTXT);
        Matcher matcherCSV = patternCSV.matcher(fileName);
        Matcher matcherTXT = patternTXT.matcher(fileName);
        if (!(matcherCSV.matches()) && fileType == 0) {
            System.out.println("Foi acrescentado a extensão .csv em falta no ficheiro "+fileName+"\n");
            return fileName.concat(".csv");
        } else if (!(matcherTXT.matches()) && fileType == 1) {
            System.out.println("Foi acrescentado a extensão .txt em falta no ficheiro "+fileName+"\n");
            return fileName.concat(".txt");
        }
        return fileName;
    }

    public static void saveCSVMenu(int resolution, int[] requestedDataArray, Date initialDate, Date finalDate, int type, int dataType) throws IOException {
        boolean out = false;
        while (!out) {
            System.out.println("\nPretende gravar a informação observada num ficheiro CSV? (S/N)");
            String option1 = in.next();
            switch (option1) {
                case "S", "s":
                    System.out.println("\nIndique o nome do ficheiro (.csv) que pretende gravar:");
                    String outputFile = in.next();
                    saveCSVbyResolution(resolution, correctFileName(outputFile, 0), requestedDataArray, initialDate, finalDate, type, dataType);
                    System.out.println("\nA sua informação foi guardada com sucesso!");
                    out = true;
                    break;
                case "N", "n":
                    out = true;
                    break;
                default:
                    System.out.println("Ocorreu um erro. Deverá escrever S(Sim) ou N(Não), querendo respectivamente guardar ou não guardar a inforzmação no ficheiro!");
                    break;
            }
        }
    }

    public static void saveCSVMenu(int resolution, int[][] requestedDataArray, Date initialDate, Date finalDate, int dataType) throws IOException {
        boolean out = false;
        while (!out) {
            System.out.println("\nPretende gravar a informação observada num ficheiro CSV? (S/N)");
            String option1 = in.next();
            switch (option1) {
                case "S", "s":
                    System.out.println("\nIndique o nome do ficheiro (.csv) que pretende gravar:");
                    String outputFile = in.next();
                    printAllInCSV(requestedDataArray, resolution, initialDate, finalDate, correctFileName(outputFile, 0), dataType);
                    System.out.println("\nA sua informação foi guardada com sucesso!");
                    out = true;
                    break;
                case "N", "n":
                    out = true;
                    break;
                default:
                    System.out.println("Ocorreu um erro. Deverá escrever S(Sim) ou N(Não), querendo respectivamente guardar ou não guardar a informação no ficheiro!");
                    break;
            }
        }
    }

    public static void saveCSVMenuComparison(int[] variationDataArrayInitial, int[] variationDataArrayFinal, int[] differenceVector,
                                             double average1, double average2, double sd1, double sd2, Date[] getDates) throws IOException {
        boolean out = false;
        while (!out) {
            System.out.println("\nPretende gravar a informação observada num ficheiro CSV? (S/N)");
            String option1 = in.next();
            switch (option1) {
                case "S", "s":
                    System.out.println("\nIndique o nome do ficheiro (.csv) que pretende gravar:");
                    String outputFile = in.next();
                    saveComparisonCSV(correctFileName(outputFile, 0), variationDataArrayInitial, variationDataArrayFinal, differenceVector, average1, average2, sd1, sd2, daysInString(getDates[0], variationDataArrayInitial.length), daysInString(getDates[2], variationDataArrayFinal.length));
                    System.out.println("\nA sua informação foi guardada com sucesso!");
                    out = true;
                    break;
                case "N", "n":
                    out = true;
                    break;
                default:
                    System.out.println("Ocorreu um erro. Deverá escrever S(Sim) ou N(Não), querendo respectivamente guardar ou não guardar a informação no ficheiro!");
                    break;
            }
        }
    }

    public static void saveCSVbyResolution(int resolution, String outputFile, int[] requestedDataArray, Date initialDate, Date finalDate, int type, int dataType) throws IOException {
        String[] names = null;
        if (dataType == 1)
            names = NAMES_CSV;
        else if (dataType == 3)
            names = NAMES_TOTAL_CSV;
        switch (resolution) {
            case 0:
                saveDailyCSV(outputFile, requestedDataArray, daysInString(initialDate, requestedDataArray.length), names[type]);
                break;
            case 1:
                saveWeeklyCSV(outputFile, requestedDataArray, weekIntervalsInString(initialDate, finalDate), names[type]);
                break;
            case 2:
                saveMonthlyCSV(outputFile, requestedDataArray, monthsInString(initialDate, requestedDataArray.length), names[type]);
                break;
        }
    }

    public static boolean validateDates(Date initialDate, Date finalDate) {
        if (finalDate.before(initialDate)) {
            System.out.println("\nERRO: Intervalo de tempo invalido\n");
            return false;
        }
        return true;
    }

    public static int isValidDate(String d) {
        String regex = "[0-2][0-9]-[1][0-2]-[0-9]{4}", regex2 = "[0-9]{4}-[1][0-2]-[0-2][0-9]",
                regex3 = "[3][0-1]-[0][1-9]-[0-9]{4}", regex4 = "[0-9]{4}-[0][1-9]-[3][0-1]",
                regex5 = "[3][0-1]-[1][0-2]-[0-9]{4}", regex6 = "[0-9]{4}-[1][0-2]-[3][0-1]",
                regex7 = "[0-2][0-9]-[0][1-9]-[0-9]{4}", regex8 = "[0-9]{4}-[0][1-9]-[0-2][0-9]";
        Pattern pattern = Pattern.compile(regex), pattern2 = Pattern.compile(regex2), pattern3 = Pattern.compile(regex3), pattern4 = Pattern.compile(regex4),
                pattern5 = Pattern.compile(regex5), pattern6 = Pattern.compile(regex6), pattern7 = Pattern.compile(regex7), pattern8 = Pattern.compile(regex8);
        Matcher matcher = pattern.matcher(d), matcher2 = pattern2.matcher(d), matcher3 = pattern3.matcher(d), matcher4 = pattern4.matcher(d),
                matcher5 = pattern5.matcher(d),matcher6 = pattern6.matcher(d),matcher7 = pattern7.matcher(d),matcher8 = pattern8.matcher(d);
        if (matcher.matches()||matcher3.matches()||matcher5.matches()||matcher7.matches())
            return 1;
        else if (matcher2.matches()||matcher4.matches()||matcher6.matches()||matcher8.matches())
            return 2;
        else
            return 0;
    }

    public static Date readDayNIM(String nextValue) throws ParseException {
        Date day = null;
        int dateType = isValidDate(nextValue);
        if (dateType == 1) {
            day = new SimpleDateFormat("dd-MM-yyyy").parse(nextValue);
        } else if (dateType == 2) {
            day = new SimpleDateFormat("yy-MM-dddd").parse(nextValue);
        }
        return day;
    }

    public static void notInteractiveMode(String[] args, Date[] dateArrayCumulative, int[][] dataArrayCumulative, Date[] dateArrayTotal, int[][] dataArrayTotal, double[][] matrixData) throws ParseException, IOException {
        int resolution = -1;
        Date di = null, df = null, di1 = null, df1 = null, di2 = null, df2 = null, previsionDate = null;
        String inputFileCumulative = null, inputFileTotal = null, inputFileMatrix = null, outputFile = null;
        boolean errorFirstParameters = false;
        int arg = 0;

        while ((arg < args.length) && !errorFirstParameters) {
            switch (args[arg]) {
                case "-r":
                    if (validateResolution(args[arg + 1]))
                        resolution = Integer.parseInt(args[arg + 1]);
                    else {
                        printErrorNIM("Resolucao invalida");
                        errorFirstParameters = true;
                    }
                    break;
                case "-di":
                    di = readDayNIM(args[arg + 1]);
                    if (di == null) {
                        printErrorNIM("Formato do -di invalido");
                        errorFirstParameters = true;
                    }
                    break;
                case "-df":
                    df = readDayNIM(args[arg + 1]);
                    if (df == null) {
                        printErrorNIM("Formato do -df invalido");
                        errorFirstParameters = true;
                    }
                    break;
                case "-di1":
                    di1 = readDayNIM(args[arg + 1]);
                    if (di1 == null) {
                        printErrorNIM("Formato do -di1 invalido");
                        errorFirstParameters = true;
                    }
                    break;
                case "-df1":
                    df1 = readDayNIM(args[arg + 1]);
                    if (df1 == null) {
                        printErrorNIM("Formato do -df1 invalido");
                        errorFirstParameters = true;
                    }
                    break;
                case "-di2":
                    di2 = readDayNIM(args[arg + 1]);
                    if (di2 == null) {
                        printErrorNIM("Formato do -di2 invalido");
                        errorFirstParameters = true;
                    }
                    break;
                case "-df2":
                    df2 = readDayNIM(args[arg + 1]);
                    if (df2 == null) {
                        printErrorNIM("Formato do -df2 invalido");
                        errorFirstParameters = true;
                    }
                    break;
                case "-T":
                    previsionDate = readDayNIM(args[arg + 1]);
                    if (previsionDate == null) {
                        printErrorNIM("Formato do -T invalido");
                        errorFirstParameters = true;
                    }
                    break;
            }
            arg++;
        }

        if (!errorFirstParameters) {

            int numberOfCalcTypes = 0;
            boolean executeVariations = false, executeComparisons = false, executePrevisons = false;
            if (resolution != -1 && di != null && df != null) {
                numberOfCalcTypes++;
                executeVariations = true;
            }
            if (di1 != null && df1 != null && di2 != null && df2 != null) {
                if (!executeVariations)
                    numberOfCalcTypes++;
                executeComparisons = true;
            }
            if ((previsionDate != null)) {
                numberOfCalcTypes+=2;
                executePrevisons = true;
            }

            int calcType = 0;
            boolean errorSecondParameters = false;
            while ((calcType <= numberOfCalcTypes) && !errorSecondParameters && numberOfCalcTypes != 0){

                if (outputFile == null) {
                    outputFile = correctFileName(args[args.length-1-calcType], 1);

                } else if (inputFileMatrix == null && executePrevisons) {
                    if (validateInputFile(args[args.length-1-calcType]))
                        inputFileMatrix = args[args.length-1-calcType];
                    else {
                        errorSecondParameters = true;
                        printErrorNIM("O ficheiro da informação da matriz de transição não é valido");
                    }
                } else if (inputFileCumulative == null && (executeVariations || executeComparisons)) {
                    if (validateInputFile(args[args.length-1-calcType]))
                        inputFileCumulative = args[args.length-1-calcType];
                    else {
                        errorSecondParameters = true;
                        printErrorNIM("O ficheiro do registo do número de acumulados não é valido");
                    }
                } else if (inputFileTotal == null && (executePrevisons)) {
                    if (validateInputFile(args[args.length-1-calcType]))
                        inputFileTotal = args[args.length-1-calcType];
                    else {
                        errorSecondParameters = true;
                        printErrorNIM("O ficheiro do registo do número de totais não é valido");
                    }
                }
                calcType++;
            }

            if (!errorSecondParameters && (executeComparisons || executeVariations || executePrevisons)) {

                PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
                int arrayLengthCumulative = 0, arrayLengthTotal = 0;
                if (executeVariations || executeComparisons)
                    arrayLengthCumulative = fillArray(dateArrayCumulative, dataArrayCumulative, inputFileCumulative, "1");
                if (executePrevisons) {
                    fillMatrix(matrixData, inputFileMatrix);
                    arrayLengthTotal = fillArray(dateArrayTotal, dataArrayTotal, inputFileTotal, "2");
                }
                if (executeVariations) {
                    if (validateDates(di, df)) {
                        if (dateRangeIsInArray(dateArrayCumulative, di, df, arrayLengthCumulative)) {
                            if (variationIsPossible(resolution, di, df)) {
                                boolean dayBefore = variationIsPossibleInDayBefore(resolution, di, df, dateArrayCumulative, arrayLengthCumulative);
                                int[][] requestedDataArray = calculateAllVariations(resolution, dateArrayCumulative, dataArrayCumulative, di, df, arrayLengthCumulative, dayBefore);
                                printVariationsInTXT(printer, requestedDataArray, resolution, di, df, dayBefore);
                            } else {
                                System.out.println("ERRO: A resolução selecionada é inválida para o número de dias do intervalo introduzido!");
                            }
                        } else {
                            System.out.printf("ERRO: Data(s) introduzidas não constam no ficheiro %s lido para o cálculo das variações!\n\n", inputFileCumulative);
                        }
                        if (executePrevisons) {
                            if (dateRangeIsInArray(dateArrayTotal, di, df, arrayLengthTotal)) {
                                int[][] requestedDataArray = getAllTotalArrayRequiredValues(new Date[]{di, df}, dateArrayTotal, dataArrayTotal, arrayLengthTotal);
                                printTotalsInTXT(printer, requestedDataArray, di, df);
                            } else {
                                System.out.printf("ERRO: Data(s) introduzidas não constam no ficheiro %s lido para o registo diário dos valores totais!\n\n", inputFileTotal);
                            }
                        }
                    }
                }
                if (executeComparisons) {
                    if (validateDates(di1, df1) && validateDates(di2, df2)) {
                        if (dateRangeIsInArray(dateArrayCumulative, di1, df1, arrayLengthCumulative) && dateRangeIsInArray(dateArrayCumulative, di2, df2, arrayLengthCumulative)) {
                            for (int type = 1; type <= COLUMN_LENGTH_DATA_ARRAY; type++)
                                calcAndPrintComparisonCumulativeTXT(printer, di1, df1, di2, df2, dateArrayCumulative, dataArrayCumulative, arrayLengthCumulative, type);
                        } else {
                            System.out.printf("ERRO: Data(s) introduzidas não constam no ficheiro %s lido para as comparações com valores acumulados!\n\n", inputFileCumulative);
                        }
                        if (executePrevisons) {
                            if (dateRangeIsInArray(dateArrayTotal, di1, df1, arrayLengthTotal) && dateRangeIsInArray(dateArrayTotal, di2, df2, arrayLengthTotal)) {
                                for (int type = 1; type <= COLUMN_LENGTH_DATA_ARRAY; type++)
                                    calcAndPrintComparisonTotalTXT(printer, di1, df1, di2, df2, dateArrayTotal, dataArrayTotal, arrayLengthTotal, type);
                            } else {
                                System.out.printf("ERRO: Data(s) introduzidas não constam no ficheiro %s lido para as comparações com valores totais!\n\n", inputFileTotal);
                            }
                        }
                    }
                }
                if (executePrevisons) {
                    // previsao de casos
                    if (previsionDate.after(dateArrayTotal[0])) {
                        double[][] dataPrediction = predictDataInDay(previsionDate, dateArrayTotal, arrayLengthTotal, matrixData, dataArrayTotal);
                        printPredictionsTXT(printer, dataPrediction, previsionDate);
                    } else {
                        System.out.println("ERRO: Não existem dados anteriores à data introduzida para efetuar previsões.");
                    }
                    // previsao de dias para morrer
                    double[][] daysTillDeath = daysToDeathMatrixM(daysToDeathMatrixN(removeDeaths(matrixData)));
                    printDaysTillDeathTXT(printer, daysTillDeath);
                }
                printer.close();
            }
        }
    }

    public static void printVariationsInTXT(PrintWriter printer, int[][] variations, int resolution, Date initialDate, Date finalDate, boolean dayBefore) {
        String type = "";
        String[] names = {"Não infetados", "Novos casos", "Novas hospit.", "Novos intern. UCI", "Novas mortes"}, datesRange = null;
        String[][] weeklyDateRange = null;
        Date[] dateRange = null;
        boolean error = false;
        switch (resolution) {
            case 0:
                datesRange = daysInString(initialDate, numberOfDays(initialDate, finalDate));
                printer.printf("Registo diário de %s a %s\n\n", datesRange[0], datesRange[datesRange.length - 1]);
                break;
            case 1:
                dateRange = findCompleteWeeks(initialDate, finalDate);
                weeklyDateRange = weekIntervalsInString(dateRange[0], dateRange[1]);
                printer.printf("Registo semanal de %s a %s\n\n", weeklyDateRange[0][0], weeklyDateRange[weeklyDateRange.length - 1][1]);
                break;
            case 2:
                dateRange = findCompleteMonth(initialDate, finalDate);
                datesRange = monthsInString(dateRange[0], numberOfMonths(new Date[]{dateRange[0], dateRange[1]}));
                printer.printf("Registo mensal de %s a %s\n\n", datesRange[0], datesRange[datesRange.length - 1]);
                break;
            default:
                error = true;
                break;
        }
        if (!error) {
            printer.printf("%-25s", "Intervalo de tempo");
            for (int line = 0; line < variations[0].length; line++)
                printer.printf("%20s", names[line]);
            printer.printf("\n");
            if (!dayBefore) {
                switch (resolution) {
                    case 0, 2:
                        printer.printf("%-23s %80s\n", datesRange[0] + ":", "Erro: Dados insuficientes para calcular a variacao.");
                        break;
                    case 1:
                        printer.printf("%s a %s: Dados insuficientes para calcular a variacao.\n", weeklyDateRange[0][0], weeklyDateRange[0][1]);
                        break;
                }
            } else {
                switch (resolution) {
                    case 0, 2:
                        printer.printf("%-23s ", datesRange[0] + ":");
                        break;
                    case 1:
                        printer.printf("%s a %s: ", weeklyDateRange[0][0], weeklyDateRange[0][1]);
                        break;
                }
                for (int column = 0; column < variations[0].length; column++) {
                    String sinal;
                    if (variations[0][column] >= 0)
                        sinal = "+";
                    else
                        sinal = "";
                    printer.printf("%20s", sinal + variations[0][column]);
                }
                printer.println();
            }
            for (int line = 1; line < variations.length; line++) {
                switch (resolution) {
                    case 0, 2:
                        printer.printf("%-23s ", datesRange[line] + ":");
                        break;
                    case 1:
                        printer.printf("%s a %s: ", weeklyDateRange[line][0], weeklyDateRange[line][1]);
                        break;
                }
                for (int column = 0; column < variations[0].length; column++) {
                    String sinal;
                    if (variations[line][column] >= 0)
                        sinal = "+";
                    else
                        sinal = "";
                    printer.printf("%20s", sinal + variations[line][column]);
                }
                printer.println();
            }
            printer.println("\n");
        }
    }

    public static void printTotalsInTXT(PrintWriter printer, int[][] dataArray, Date initialDate, Date finalDate) {
        String[] datesRange = null;
        datesRange = daysInString(initialDate, numberOfDays(initialDate, finalDate));
        printer.printf("Registo diário de total de casos de %s a %s\n\n", datesRange[0], datesRange[datesRange.length - 1]);
        printer.printf("%-25s", "Intervalo de tempo");
        for (int line = 0; line < dataArray[0].length; line++)
            printer.printf("%20s", NAMES_TOTAL[line]);
        printer.printf("\n");
        for (int line = 0; line < dataArray.length; line++) {
            printer.printf("%-23s ", datesRange[line] + ":");
            for (int column = 0; column < dataArray[0].length; column++) {
                printer.printf("%20s", dataArray[line][column]);
            }
            printer.println();
        }
        printer.println("\n");
    }

    public static void printDaysTillDeathTXT(PrintWriter printer, double[][] daysTillDeath) {
        String[] transientStates = {"Não infetado", "Infetado", "Hospitalizado", "Internado na UCI"};
        printer.println("-------------------Número esperado de dias ate chegar ao estado obito------------------\n");
        for (int i = 0; i < transientStates.length; i++)
            printer.printf("%-20s", transientStates[i]);
        printer.println();
        for (int i = 0; i < daysTillDeath[0].length; i++)
            printer.printf("%-20.1f", daysTillDeath[0][i]);
        printer.println("\n\n");
    }

    public static void printPredictionsTXT(PrintWriter printer, double[][] dataPrediction, Date datePrediction) {
        printer.printf("----------------------------Previsão para o dia %s----------------------------\n\n", daysInString(datePrediction, 1)[0]);
        for (int type = 0; type < COLUMN_LENGTH_DATA_ARRAY; type++) {
            printer.printf("%-20s", NAMES_TOTAL[type]);
        }
        printer.println();
        for (int typeData = 0; typeData < COLUMN_LENGTH_DATA_ARRAY; typeData++) {
            printer.printf("%-20.1f", dataPrediction[typeData][0]);
        }
        printer.println("\n\n");
    }

    public static void calcAndPrintComparisonCumulativeTXT(PrintWriter printer, Date di1, Date df1, Date di2, Date df2, Date[] dateArrayAcumulado, int[][] dataArrayAcumulado, int arrayLengthAcumulado, int type) {
        Date[] dates = {di1, df1, di2, df2};
        sameSizeIntervals(dates);
        boolean dayBefore = variationIsPossibleInDayBefore(0, dates[0], dates[1], dateArrayAcumulado, arrayLengthAcumulado);
        int[] requestedDataArray1 = dailyFluctuation(dataArrayAcumulado, dateArrayAcumulado, dates[0], dates[1], arrayLengthAcumulado, type - 1, dayBefore);
        int[] requestedDataArray2 = dailyFluctuation(dataArrayAcumulado, dateArrayAcumulado, dates[2], dates[3], arrayLengthAcumulado, type - 1, dayBefore);
        int[] difference = differenceVector(requestedDataArray1, requestedDataArray2);
        double average1 = calculateAverage(requestedDataArray1, dayBefore);
        double average2 = calculateAverage(requestedDataArray2, dayBefore);
        double sd1 = calculateStandardDeviation(average1, requestedDataArray1, dayBefore);
        double sd2 = calculateStandardDeviation(average2, requestedDataArray2, dayBefore);
        printComparisonTXT(printer, requestedDataArray1, requestedDataArray2, difference, average1, average2, sd1, sd2, daysInString(dates[0], requestedDataArray1.length), daysInString(dates[2], requestedDataArray2.length), dayBefore, 0,type);
    }

    public static void calcAndPrintComparisonTotalTXT(PrintWriter printer,Date di1, Date df1, Date di2, Date df2, Date[] dateArrayTotal, int[][] dataArrayTotal, int arrayLengthTotal, int type){
        Date[] dates = {di1, df1, di2, df2};
        sameSizeIntervals(dates);
        boolean dayBefore = variationIsPossibleInDayBefore(0, dates[0], dates[1], dateArrayTotal, arrayLengthTotal);
        if (!dayBefore)
            dates[0] = addDays(dates[0], 1);
        int[] requestedDataArray1 = copyArrayContent(dataArrayTotal, dateArrayTotal, dates[0], dates[1], arrayLengthTotal, type - 1);
        int[] requestedDataArray2 = copyArrayContent(dataArrayTotal, dateArrayTotal, dates[2], dates[3], arrayLengthTotal, type - 1);
        int[] difference = differenceVector(requestedDataArray1, requestedDataArray2);
        double average1 = calculateAverage(requestedDataArray1, dayBefore);
        double average2 = calculateAverage(requestedDataArray2, dayBefore);
        double sd1 = calculateStandardDeviation(average1, requestedDataArray1, dayBefore);
        double sd2 = calculateStandardDeviation(average2, requestedDataArray2, dayBefore);
        if (!dayBefore)
            dates[0] = addDays(dates[0], -1);
        printComparisonTXT(printer ,requestedDataArray1, requestedDataArray2, difference, average1, average2, sd1, sd2, daysInString(dates[0], requestedDataArray1.length), daysInString(dates[2], requestedDataArray2.length), dayBefore, 1,type);
    }

    public static void printComparisonTXT(PrintWriter printer, int[] requestedDataArray1, int[] requestedDataArray2, int[] difference, double average1, double average2, double sd1, double sd2, String[] daysInString, String[] daysInString2, boolean dayBefore, int dateType, int type) {
        String[] dateTypeArray = {"dados acumulados", "dados totais"};
        String[] names;
        if (dateType == 0)
            names = NAMES;
        else
            names = NAMES_TOTAL;
        printer.printf("Comparação diária de %s entre %s / %s e %s / %s para %s:\n\n",dateTypeArray[dateType] ,daysInString[0], daysInString[requestedDataArray1.length - 1], daysInString2[0], daysInString2[requestedDataArray2.length - 1], names[type-1]);
        printer.printf("%14s |%14s |%10s |%10s |%10s\n", "Dia Período 1", "Dia Período 2", "Período 1", "Período 2", "Diferença");
        if (!dayBefore) {
            printer.printf("%14s |%14s | Erro: Dados insuficientes para calcular a variacao a %s.\n", daysInString[0], daysInString2[0], daysInString[0]);
        } else {
            printer.printf("%14s |%14s |%10d |%10d |%10d\n", daysInString[0], daysInString2[0], requestedDataArray1[0], requestedDataArray2[0], difference[0]);
        }
        for (int i = 1; i < requestedDataArray1.length; i++) {
            printer.printf("%14s |%14s |%10d |%10d |%10d\n", daysInString[i], daysInString2[i], requestedDataArray1[i], requestedDataArray2[i], difference[i]);
        }
        printer.printf("\nPeríodo %s a %s: Média: %.4f | Desvio Padrão: %.4f", daysInString[0], daysInString[requestedDataArray1.length - 1], average1, sd1);
        printer.printf("\nPeríodo %s a %s: Média: %.4f | Desvio Padrão: %.4f\n\n\n", daysInString2[0], daysInString2[requestedDataArray2.length - 1], average2, sd2);
    }

    public static boolean validateInputFile(String inputFile) {
        if (!(new File(inputFile).exists())) {
            return false;
        }
        return true;
    }

    public static boolean validateResolution(String resolution) {
        switch (resolution) {
            case "0", "1", "2":
                return true;
            default:
                return false;
        }
    }

    private static void printErrorNIM(String error) {
        System.out.printf("\nErro: %s\n\n", error);
        System.out.println("O programa tem de ter a seguinte sintaxe:");
        System.out.println("java ProjetoLAPR1.jar -jar -r X -di DD-MM-AAAA -df DD-MM-AAAA");
        System.out.println("-di1 DD-MM-AAAA -df1 DD-MM-AAAA -di2 DD-MM-AAAA -df2 DD-MM-AAAA -T DD-MM-AAAA");
        System.out.println("registoNumeroTotalCovid19.csv registoNumerosAcumuladosCovid19.csv matrizTransicao.txt nomeFicheiroSaida.txt\n\n");
    }

    public static String[][] weekIntervalsInString(Date initialDate, Date finalDate) {
        Date[] rangeDateCompleteWeeks = findCompleteWeeks(initialDate, finalDate);
        int numberOfWeeks = numberOfWeeks(rangeDateCompleteWeeks);
        String[][] weekIntervalsInString = new String[numberOfWeeks][2];
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(rangeDateCompleteWeeks[0]);
        for (int week = 0; week < numberOfWeeks; week++) {
            weekIntervalsInString[week][0] = String.format("%s", formatter.format(c.getTime()));
            c.add(Calendar.DATE, 6);
            weekIntervalsInString[week][1] = String.format("%s", formatter.format(c.getTime()));
            c.add(Calendar.DATE, 1);
        }
        return weekIntervalsInString;
    }

    public static String[] daysInString(Date initialDate, int requestedDataArrayLength) {
        String[] dayInString = new String[requestedDataArrayLength];
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(initialDate);
        for (int day = 0; day < dayInString.length; day++) {
            dayInString[day] = String.format("%s", formatter.format(c.getTime()));
            c.add(Calendar.DATE, 1);
        }
        return dayInString;
    }

    public static String[] monthsInString(Date initialDate, int requestedDataArrayLength) {
        String[] monthsInString = new String[requestedDataArrayLength];
        SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(initialDate);
        for (int month = 0; month < monthsInString.length; month++) {
            monthsInString[month] = String.format("%s", formatter.format(c.getTime()));
            c.add(Calendar.MONTH, 1);
        }
        return monthsInString;
    }

    public static void saveComparisonCSV(String outputFileName, int[] variationDataArrayInitial, int[] variationDataArrayFinal, int[] differenceVector,
                                         double average1, double average2, double sd1, double sd2, String[] daysInString, String[] daysInString2) throws IOException {
        String av1 = String.format("%.4f", average1), av2 = String.format("%.4f", average2), sds1 = String.format("%.4f", sd1), sds2 = String.format("%.4f", sd2);

        File outputFile = new File(outputFileName);
        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("dia_periodo1,dia_periodo2,periodo1,periodo2,diferenca_periodos\n");
        for (int i = 0; i < variationDataArrayInitial.length; i++)
            bw.write(String.format("%s,%s,%d,%d,%d\n", daysInString[i], daysInString2[i], variationDataArrayInitial[i], variationDataArrayFinal[i], differenceVector[i]));

        bw.write(String.format("media,,%s,%s\ndevio_padrao,,%s,%s\n", av1.replace(",", "."), av2.replace(",", "."), sds1.replace(",", "."), sds2.replace(",", ".")));
        bw.close();
    }

    public static void saveDailyCSV(String outputFileName, int[] requestedDataArray, String[] daysInString, String dataType) throws IOException {
        File outputFile = new File(outputFileName);
        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.format("dia,%s\n", dataType));
        for (int i = 0; i < requestedDataArray.length; i++)
            bw.write(String.format("%s,%d\n", daysInString[i], requestedDataArray[i]));
        bw.close();
    }

    public static void saveWeeklyCSV(String outputFileName, int[] requestedDataArray, String[][] weekIntervalsInString, String dataType) throws IOException {
        File outputFile = new File(outputFileName);
        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.format("semana,%s\n", dataType));
        for (int i = 0; i < requestedDataArray.length; i++)
            bw.write(String.format("%s_%s,%d\n", weekIntervalsInString[i][0], weekIntervalsInString[i][1], requestedDataArray[i]));
        bw.close();
    }

    public static void saveMonthlyCSV(String outputFileName, int[] requestedDataArray, String[] monthsInString, String dataType) throws IOException {
        File outputFile = new File(outputFileName);
        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.format("mes,%s\n", dataType));
        for (int i = 0; i < requestedDataArray.length; i++)
            bw.write(String.format("%s,%d\n", monthsInString[i], requestedDataArray[i]));
        bw.close();
    }

    public static void printAllInCSV(int[][] variations, int resolution, Date initialDate, Date finalDate, String outputFile, int dataType) throws IOException {
        PrintWriter printer = new PrintWriter(outputFile);
        String[] datesRange = null;
        String[][] weeklyDateRange = null;
        Date[] dateRange = null;
        String stringResolution = null;
        String[] names = null;
        if (dataType == 1)
            names = NAMES_CSV;
        else if (dataType == 3)
            names = NAMES_TOTAL_CSV;
        boolean error = false;
        switch (resolution) {
            case 0:
                datesRange = daysInString(initialDate, numberOfDays(initialDate, finalDate));
                stringResolution = "dia";
                break;
            case 1:
                dateRange = findCompleteWeeks(initialDate, finalDate);
                weeklyDateRange = weekIntervalsInString(dateRange[0], dateRange[1]);
                stringResolution = "mes";
                break;
            case 2:
                dateRange = findCompleteMonth(initialDate, finalDate);
                datesRange = monthsInString(dateRange[0], numberOfMonths(new Date[]{dateRange[0], dateRange[1]}));
                stringResolution = "semana";
                break;
            default:
                error = true;
                break;
        }
        if (!error) {
            assert names != null;
            printer.printf("%s,%s,%s,%s,%s,%s\n", stringResolution, names[0], names[1], names[2], names[3], names[4]);
            for (int line = 0; line < variations.length; line++) {
                if (resolution == 0 || resolution == 2)
                    printer.printf("%s,%d,%d,%d,%d,%d\n", datesRange[line], variations[line][0], variations[line][1], variations[line][2], variations[line][3], variations[line][4]);
                if (resolution == 1)
                    printer.printf("%s_%s,%d,%d,%d,%d,%d\n", weeklyDateRange[line][0], weeklyDateRange[line][1], variations[line][0], variations[line][1], variations[line][2], variations[line][3], variations[line][4]);
            }
        }
        printer.close();
    }

    public static int fileVerification(Date[] dateArray, int[][] dataArray, String option) throws IOException, ParseException {

        int arraysLength = 0;
        boolean flag = true;
        while (flag) {
            System.out.println("\nIntroduza o nome do ficheiro CSV:");
            String file = in.next();
            File testFile = new File(file);
            if (testFile.exists()) {
                arraysLength = fillArray(dateArray, dataArray, file, option);
                System.out.println("\n------------Descarregando-------------");
                System.out.println("----------Processo concluido----------");
                flag = false;
            } else {
                System.out.println("\nERRO: O nome do ficheiro introduzido não existe!");
            }
        }
        return arraysLength;
    }

    public static void fileVerification(double[][] dataArray) throws IOException, ParseException {
        boolean flag = true;
        while (flag) {
            System.out.println("\nIntroduza o nome do ficheiro txt:");
            String file = in.next();
            File testFile = new File(file);
            if (testFile.exists()) {
                fillMatrix(dataArray, file);
                System.out.println("\n------------Descarregando-------------");
                System.out.println("----------Processo concluido----------");
                flag = false;
            } else {
                System.out.println("\nERRO: O nome do ficheiro introduzido não existe!");
            }
        }
    }

    public static boolean checkIfNull(Date[] Arrayteste) {
        boolean empty = true;
        for (Date d : Arrayteste) {
            if (d != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    public static boolean checkIfNull(double[][] matrix) {
        boolean empty = true;

        for (int i = 0; i < COLUMN_LENGTH_DATA_ARRAY; i++) {
            for (int j = 0; j < COLUMN_LENGTH_DATA_ARRAY; j++) {
                if (matrix[i][j] != 0) {
                    empty = false;
                    break;
                }
            }
        }
        return empty;
    }

    public static Date readDay() throws ParseException {
        String day;
        Date date1 = null;
        boolean validDate = false;
        while (!validDate) {
            day = in.next();
            int dateType = isValidDate(day);
            if (dateType == 1) {
                date1 = new SimpleDateFormat("dd-MM-yyyy").parse(day);
                validDate = true;
            } else if (dateType == 2) {
                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(day);
                validDate = true;
            } else {
                System.out.println("\nERRO: O formato da data introduzida está errada, utilize o formato dd-MM-aaaa ou aaaa-MM-dd!");
                System.out.println("\nPor favor, introduza uma nova data:");
            }
        }
        return date1;
    }

    public static Date readPredictionDay(Date[] dateArrayTotal) throws ParseException {
        boolean validDate = false;
        Date date = null;
        while (!validDate) {
            date = readDay();
            if (date.after(dateArrayTotal[0]))
                validDate = true;
            else {
                System.out.println("\nERRO: Não existem dados anteriores à data introduzida para efetuar previsões.");
                System.out.println("\nPor favor, insira uma nova data:");
            }
        }
        return date;
    }

    public static void printPredictions(int calcType, double[][] dataPrediction, Date datePrediction) {

        if (calcType == 5) {
            System.out.printf("\n----------------------------Previsão para o dia %s----------------------------\n\n", daysInString(datePrediction, 1)[0]);
            for (int type = 0; type < COLUMN_LENGTH_DATA_ARRAY; type++) {
                System.out.printf("%-20s", NAMES_TOTAL[type]);
            }
            System.out.println();
            for (int typeData = 0; typeData < COLUMN_LENGTH_DATA_ARRAY; typeData++) {
                System.out.printf("%-20.1f", dataPrediction[typeData][0]);
            }
            System.out.println();
        } else {
            System.out.printf("\nTotal para o dia %s\n\n", daysInString(datePrediction, 1)[0]);
            System.out.printf("%s: %.1f\n", NAMES_TOTAL[calcType], dataPrediction[calcType][0]);
        }
    }

    public static Date addDays(Date date1, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    public static Date addMonth(Date date1, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        c.add(Calendar.MONTH, i);
        return c.getTime();
    }

    public static int[] copyArrayContent(int[][] dataArray, Date[] dateArray, Date initialDate, Date finalDate, int arrayLength, int dataType) {
        int[] indexes = calculateIndexRange(dateArray, initialDate, finalDate, arrayLength);

        int[] copiedArray = new int[indexes[1] - indexes[0] + 1];
        for (int index = indexes[0], i = 0; index <= indexes[1]; index++, i++) {
            copiedArray[i] = dataArray[index][dataType];
        }
        return copiedArray;
    }

    public static void sameSizeIntervals(Date[] getDates) {
        int interv1 = numberOfDays(getDates[0], getDates[1]);
        int interv2 = numberOfDays(getDates[2], getDates[3]);
        Calendar c = Calendar.getInstance();

        if (interv1 > interv2) {
            c.setTime(getDates[1]);
            c.add(Calendar.DATE, -(interv1 - interv2));
            getDates[1] = c.getTime();
        } else if (interv1 < interv2) {
            c.setTime(getDates[3]);
            c.add(Calendar.DATE, -(interv2 - interv1));
            getDates[3] = c.getTime();
        }
    }

    public static int fillArray(Date[] dateArray, int[][] dataArray, String fileName, String option) throws IOException, ParseException {
        BufferedReader bf = new BufferedReader(new FileReader(fileName));
        String line = bf.readLine();
        int counter = 0;
        while ((line = bf.readLine()) != null) {
            String[] values = line.split(",");
            if (option.equals("1")) {
                dateArray[counter] = new SimpleDateFormat("yyyy-MM-dd").parse(values[DATA_INDEX]);
            } else {
                dateArray[counter] = new SimpleDateFormat("dd-MM-yyy").parse(values[DATA_INDEX]);
            }
            dataArray[counter][DIARIO_NAO_INFETADO_INDEX - 1] = Integer.parseInt(values[DIARIO_NAO_INFETADO_INDEX]);
            dataArray[counter][ACUMULADO_INFETADO_INDEX - 1] = Integer.parseInt(values[ACUMULADO_INFETADO_INDEX]);
            dataArray[counter][ACUMULADO_HOSPITALIZADO_INDEX - 1] = Integer.parseInt(values[ACUMULADO_HOSPITALIZADO_INDEX]);
            dataArray[counter][ACUMULADO_INTERNADO_UCI_INDEX - 1] = Integer.parseInt(values[ACUMULADO_INTERNADO_UCI_INDEX]);
            dataArray[counter][ACUMULADO_MORTES_INDEX - 1] = Integer.parseInt(values[ACUMULADO_MORTES_INDEX]);
            counter++;
        }
        bf.close();
        return counter;

    }

    public static int[] dailyFluctuation(int[][] dataArray, Date[] dateArray, Date initialDate, Date finalDate, int arrayLength, int dataType, boolean dayBefore) {
        int[] indexes = calculateIndexRange(dateArray, initialDate, finalDate, arrayLength);

        int[] dailyFluctuation = new int[indexes[1] - indexes[0] + 1];
        if (!dayBefore)
            dailyFluctuation[0] = 0;
        else
            dailyFluctuation[0] = variation(dataArray, indexes[0], indexes[0] - 1, dataType);
        for (int index = indexes[0], i = 1; index < indexes[1]; index++, i++) {
            dailyFluctuation[i] = variation(dataArray, index + 1, index, dataType);
        }
        return dailyFluctuation;
    }

    public static int[] calculateIndexRange(Date[] dateArray, Date initialDate, Date finalDate, int arrayLength) {
        int[] indexes = {-1, -1};
        for (int index = 0; (index < arrayLength); index++) {
            if (dateArray[index].compareTo(initialDate) == 0)
                indexes[0] = index;
            if (dateArray[index].compareTo(finalDate) == 0)
                indexes[1] = index;
        }
        return indexes;
    }

    public static int variation(int[][] dataArray, int finalIndex, int initialIndex, int dataType) {
        return dataArray[finalIndex][dataType] - dataArray[initialIndex][dataType];
    }

    public static int[] weeklyFluctuation(int[][] dataArray, Date[] dateArray, Date initialDate, Date finalDate, int dataType, int arrayLength, boolean dayBefore) {
        Date[] rangeDate = findCompleteWeeks(initialDate, finalDate);
        int[] indexRange = calculateIndexRange(dateArray, rangeDate[0], rangeDate[1], arrayLength);
        int numberOfWeeks = numberOfWeeks(rangeDate);
        int[] weeklyFluctuation = new int[numberOfWeeks];
        if (!dayBefore)
            weeklyFluctuation[0] = 0;
        else
            weeklyFluctuation[0] = variation(dataArray, indexRange[0] + 6, indexRange[0] - 1, dataType);
        for (int week = 1; week < numberOfWeeks; week++)
            weeklyFluctuation[week] = variation(dataArray, indexRange[0] + 6 + (week) * 7, indexRange[0] - 1 + (week) * 7, dataType);
        return weeklyFluctuation;
    }

    public static Date[] findCompleteWeeks(Date initialDate, Date finalDate) {
        Date startDate = initialDate, lastDate = finalDate;
        boolean startFinder = false;
        Calendar c = Calendar.getInstance();
        c.setTime(initialDate);
        while (!c.getTime().after(finalDate)) {
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && !startFinder) {
                startFinder = true;
                startDate = c.getTime();
            }
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                lastDate = c.getTime();

            }
            c.add(Calendar.DATE, 1);
        }
        return new Date[]{startDate, lastDate};
    }

    public static boolean weekIsComplete(Date initialDate, Date finalDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(initialDate);
        while (!c.getTime().after(finalDate)) {
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                while (!c.getTime().after(finalDate)) {
                    if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                        return true;
                    c.add(Calendar.DATE, 1);
                }
                return false;
            }
            c.add(Calendar.DATE, 1);
        }
        return false;
    }

    public static int numberOfWeeks(Date[] rangeDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(rangeDate[0]);
        int numberOfWeeks = 0;
        while (!c.getTime().after(rangeDate[1])) {
            numberOfWeeks++;
            c.add(Calendar.DATE, 7);
        }
        return numberOfWeeks;
    }

    public static int[] monthlyFluctuation(int[][] dataArray, Date[] dateArray, Date initialDate, Date finalDate, int dataType, int arrayLength, boolean dayBefore) {
        int finalSum = 0, initialSum = 0;
        Date[] rangeDate = findCompleteMonth(initialDate, finalDate);
        Calendar c = Calendar.getInstance();
        c.setTime(rangeDate[0]);
        int[] indexRange = calculateIndexRange(dateArray, rangeDate[0], rangeDate[1], arrayLength);
        int numberOfMonths = numberOfMonths(rangeDate);
        int[] monthlyFluctuation = new int[numberOfMonths];
        if (!dayBefore) {
            monthlyFluctuation[0] = 0;
            c.add(Calendar.MONTH, 1);
        } else {
            finalSum += c.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;
            monthlyFluctuation[0] = variation(dataArray, indexRange[0] + (finalSum), indexRange[0] + (initialSum - 1), dataType);
            initialSum += c.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;
            c.add(Calendar.MONTH, 1);
        }
        for (int month = 1; month < numberOfMonths; month++) {
            finalSum += c.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;
            monthlyFluctuation[month] = variation(dataArray, indexRange[0] + (finalSum), indexRange[0] + (initialSum - 1), dataType);
            initialSum += c.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;
            c.add(Calendar.MONTH, 1);
        }
        return monthlyFluctuation;

    }

    public static boolean monthIsComplete(Date initialDate, Date finalDate) {
        boolean startFinder = false, endFinder = false;
        Calendar c = Calendar.getInstance();
        c.setTime(initialDate);

        while (!c.getTime().after(finalDate) && !(startFinder && endFinder)) {
            if (c.get(Calendar.DAY_OF_MONTH) == 1)
                startFinder = true;
            if (c.get(Calendar.DAY_OF_MONTH) == c.getActualMaximum(Calendar.DAY_OF_MONTH))
                endFinder = true;
            c.add(Calendar.DATE, 1);
        }
        return startFinder && endFinder;
    }

    public static Date[] findCompleteMonth(Date initialDate, Date finalDate) {
        Date startDate = initialDate, lastDate = finalDate;
        boolean startFinder = false;
        Calendar c = Calendar.getInstance();
        c.setTime(initialDate);

        while (!c.getTime().after(finalDate)) {
            if (c.get(Calendar.DAY_OF_MONTH) == 1 && !startFinder) {
                startFinder = true;
                startDate = c.getTime();
            }
            if (c.get(Calendar.DAY_OF_MONTH) == c.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                lastDate = c.getTime();
            }
            c.add(Calendar.DATE, 1);
        }
        return new Date[]{startDate, lastDate};
    }

    public static int numberOfMonths(Date[] rangeDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(rangeDate[0]);
        int numberOfMonths = 0;
        while (!c.getTime().after(rangeDate[1])) {
            numberOfMonths++;
            c.add(Calendar.MONTH, 1);
        }
        return numberOfMonths;
    }

    public static int[] differenceVector(int[] variationDataArrayInitial, int[] variationDataArrayFinal) {
        int[] differenceArray = new int[variationDataArrayInitial.length];
        for (int day = 0; day < differenceArray.length; day++) {
            differenceArray[day] = variationDataArrayFinal[day] - variationDataArrayInitial[day];
        }
        return differenceArray;
    }

    public static double calculateAverage(int[] variationDataArray, boolean dayBefore) {
        int sum = 0, nElements;
        if (!dayBefore) {
            nElements = variationDataArray.length - 1;
            for (int index = 1; index < variationDataArray.length; index++) {
                sum += variationDataArray[index];
            }
        } else {
            nElements = variationDataArray.length;
            for (int index = 0; index < variationDataArray.length; index++) {
                sum += variationDataArray[index];
            }
        }

        return ((double) sum) / (nElements);
    }

    public static double calculateStandardDeviation(double average, int[] variationDataArray, boolean dayBefore) {
        double sum = 0, nElements;
        if (!dayBefore) {
            nElements = variationDataArray.length - 1;
            for (int day = 1; day < variationDataArray.length; day++)
                sum += Math.pow((variationDataArray[day] - average), 2);
        } else {
            nElements = variationDataArray.length;
            for (int day = 0; day < variationDataArray.length; day++)
                sum += Math.pow((variationDataArray[day] - average), 2);
        }
        return Math.sqrt(sum / nElements);
    }

    public static boolean dateRangeIsInArray(Date[] dateArray, Date initialDate, Date finalDate, int arrayLength) {
        boolean isInitialDate = false, isFinalDate = false;

        for (int index = 0; index < arrayLength; index++) {
            if (initialDate.equals(dateArray[index]))
                isInitialDate = true;
            if (finalDate.equals(dateArray[index]))
                isFinalDate = true;
        }
        return isInitialDate && isFinalDate;
    }

    public static boolean variationIsPossibleInDayBefore(int resolution, Date initialDate, Date finalDate, Date[] dateArray, int arrayLength) {
        Date[] dateRange = null;
        int[] indexRange;
        switch (resolution) {
            case 0:
                indexRange = calculateIndexRange(dateArray, initialDate, finalDate, arrayLength);
                if (indexRange[0] == 0)
                    return false;
                return true;
            case 1:
                if (weekIsComplete(initialDate, finalDate)) {
                    dateRange = findCompleteWeeks(initialDate, finalDate);
                    indexRange = calculateIndexRange(dateArray, dateRange[0], dateRange[1], arrayLength);
                    if (indexRange[0] == 0)
                        return false;
                    return true;
                } else return false;
            case 2:
                if (monthIsComplete(initialDate, finalDate)) {
                    dateRange = findCompleteMonth(initialDate, finalDate);
                    indexRange = calculateIndexRange(dateArray, dateRange[0], dateRange[1], arrayLength);
                    if (indexRange[0] == 0)
                        return false;
                    return true;
                } else return false;
            default:
                return false;
        }
    }

    public static boolean variationIsPossible(int resolution, Date initialDate, Date finalDate) {
        switch (resolution) {
            case 0:
                return true;
            case 1:
                return weekIsComplete(initialDate, finalDate);
            case 2:
                return monthIsComplete(initialDate, finalDate);
            default:
                return false;
        }
    }

    public static int[][] calculateAllVariations(int resolution, Date[] dateArray, int[][] dataArray, Date initialdate, Date finalDate, int arraysLength, boolean dayBefore) {
        int[][] variations = null;
        int[] temporaryVariation = null;

        for (int type = 0; type < COLUMN_LENGTH_DATA_ARRAY; type++) {
            switch (resolution) {
                case 0:
                    temporaryVariation = dailyFluctuation(dataArray, dateArray, initialdate, finalDate, arraysLength, type, dayBefore);
                    break;
                case 1:
                    temporaryVariation = weeklyFluctuation(dataArray, dateArray, initialdate, finalDate, type, arraysLength, dayBefore);
                    break;
                case 2:
                    temporaryVariation = monthlyFluctuation(dataArray, dateArray, initialdate, finalDate, type, arraysLength, dayBefore);
                    break;
            }
            if (variations == null)
                variations = new int[temporaryVariation.length][COLUMN_LENGTH_DATA_ARRAY];
            for (int index = 0; index < temporaryVariation.length; index++) {
                variations[index][type] = temporaryVariation[index];
            }
        }
        return variations;
    }

    public static int numberOfDays(Date initialDate, Date finalDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(initialDate);
        int numberOfDays = 0;
        while (!c.getTime().after(finalDate)) {
            numberOfDays++;
            c.add(Calendar.DATE, 1);
        }
        return numberOfDays;
    }

    public static void fillMatrix(double[][] dataArray, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        int counter = 0;
        for (int i = 0; i < COLUMN_LENGTH_DATA_ARRAY; i++) {
            while ((counter < COLUMN_LENGTH_DATA_ARRAY)) {
                String[] values;
                line = br.readLine();
                if (!line.equals("")) {
                    values = line.split("=");
                    dataArray[i][counter] = Double.parseDouble(values[1]);
                    counter++;
                }

            }
            counter = 0;
        }
    }

    public static double[][] powerMatrix(double[][] matrix, int days) {
        double[][] finalMatrix = duplicateMatrix(matrix);
        double[][] temporaryMatrix = duplicateMatrix(matrix);
        double sum = 0;
        for (int k = 1; k < days; k++) {
            for (int lineFirstMatrix = 0; lineFirstMatrix < matrix.length; lineFirstMatrix++) {
                for (int columnSecondMatrix = 0; columnSecondMatrix < matrix[0].length; columnSecondMatrix++) {
                    for (int l = 0; l < matrix.length; l++)
                        sum += temporaryMatrix[lineFirstMatrix][l] * matrix[l][columnSecondMatrix];
                    finalMatrix[lineFirstMatrix][columnSecondMatrix] = sum;
                    sum = 0;
                }
            }
            temporaryMatrix = duplicateMatrix(finalMatrix);
        }
        return finalMatrix;
    }

    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        double[][] finalMatrix = new double[matrix1.length][matrix2[0].length];
        double sum = 0;
        for (int lineFirstMatrix = 0; lineFirstMatrix < matrix1.length; lineFirstMatrix++) {
            for (int columnSecondMatrix = 0; columnSecondMatrix < matrix2[0].length; columnSecondMatrix++) {
                for (int l = 0; l < matrix1[0].length; l++)
                    sum += matrix1[lineFirstMatrix][l] * matrix2[l][columnSecondMatrix];
                finalMatrix[lineFirstMatrix][columnSecondMatrix] = sum;
                sum = 0;
            }
        }
        return finalMatrix;
    }

    public static double[][] duplicateMatrix(double[][] matrix) {
        double[][] newMatrix = new double[matrix.length][matrix[0].length];
        for (int line = 0; line < matrix.length; line++) {
            for (int column = 0; column < matrix[line].length; column++)
                newMatrix[line][column] = matrix[line][column];
        }
        return newMatrix;
    }

    public static boolean dateIsInArray(Date[] dateArray, Date date, int arrayLength) {
        for (int index = 0; index < arrayLength; index++) {
            if (date.equals(dateArray[index]))
                return true;
        }
        return false;
    }

    public static double[][] predictDataInDay(Date predictionsDate, Date[] dateArray, int arrayLength, double[][] matrixData, int[][] dataArray) {
        Date historyDate = null;
        if (dateIsInArray(dateArray, predictionsDate, arrayLength)) {
            historyDate = addDays(predictionsDate, -1);
        } else {
            historyDate = dateArray[arrayLength - 1];
        }
        int index = calculateIndex(dateArray, historyDate, arrayLength);
        double[][] historicData = historicData(index, dataArray);
        int days = numberOfDays(historyDate, predictionsDate) - 1;
        double[][] newMatrixData = powerMatrix(matrixData, days);
        double[][] predictionsData = multiplyMatrices(newMatrixData, historicData);
        return predictionsData;
    }

    public static int calculateIndex(Date[] dateArray, Date date, int arrayLength) {
        int index = -1;
        for (int i = 0; (i < arrayLength); i++) {
            if (dateArray[i].compareTo(date) == 0)
                index = i;
        }
        return index;
    }

    public static double[][] historicData(int index, int[][] dataArray) {
        double[][] historicData = new double[dataArray[0].length][1];
        for (int i = 0; i < dataArray[0].length; i++)
            historicData[i][0] = dataArray[index][i];
        return historicData;
    }

    public static double[][] removeDeaths(double[][] matrixData) {
        double[][] removeDeathsMatrix = new double[matrixData.length - 1][matrixData[0].length - 1];
        for (int line = 0; line < removeDeathsMatrix.length; line++) {
            for (int column = 0; column < removeDeathsMatrix[0].length; column++)
                removeDeathsMatrix[line][column] = matrixData[line][column];
        }
        return removeDeathsMatrix;
    }

    public static double[][] subtractMatrices(double[][] matrix1, double[][] matrix2) {
        double[][] subtractedMatrix = new double[matrix1.length][matrix1[0].length];
        for (int line = 0; line < subtractedMatrix.length; line++) {
            for (int column = 0; column < subtractedMatrix[0].length; column++)
                subtractedMatrix[line][column] = matrix1[line][column] - matrix2[line][column];
        }
        return subtractedMatrix;
    }

    public static int[][] getAllTotalArrayRequiredValues(Date[] selectedDates, Date[] dateArray, int[][] dataArray, int arrayLength) {
        int[] indexes = calculateIndexRange(dateArray, selectedDates[0], selectedDates[1], arrayLength);
        int[][] result = new int[indexes[1] - indexes[0] + 1][COLUMN_LENGTH_DATA_ARRAY];
        for (int index = indexes[0], i = 0; index <= indexes[1]; index++, i++) {
                for (int j = 0; j < COLUMN_LENGTH_DATA_ARRAY; j++) {
                    result[i][j] = dataArray[index][j];
                }
        }
        return result;
    }

    public static double[][][] luDecomposition(double[][] matrix) {
        int size = matrix.length;
        double[][] upper = new double[size][size];
        double[][] lower = new double[size][size];
        for (int order = 0; order < size; order++) {
            //lower
            for (int lineLower = order; lineLower < size; lineLower++) {
                double sum = 0;
                for (int columnLower = 0; columnLower < order; columnLower++)
                    sum += lower[lineLower][columnLower] * upper[columnLower][order];
                lower[lineLower][order] = matrix[lineLower][order] - sum;
            }
            //upper
            for (int columnUpper = order; columnUpper < size; columnUpper++) {
                if (order == columnUpper)
                    upper[order][order] = 1;
                else {
                    double sum = 0;
                    for (int lineUpper = 0; lineUpper < columnUpper; lineUpper++) {
                        sum += lower[order][lineUpper] * upper[lineUpper][columnUpper];
                    }
                    upper[order][columnUpper] = (matrix[order][columnUpper] - sum) / lower[order][order];
                }
            }
        }
        return new double[][][]{upper, lower};
    }

    public static double[][] invertLower(double[][] matrixLower) {
        int size = matrixLower.length;
        double[][] invertedMatrix = new double[size][size];

        for (int line = 0; line < size; line++) {
            for (int column = 0; column <= line; column++) {
                if (line == column)
                    invertedMatrix[line][column] = 1 / matrixLower[line][column];
                else {
                    double sum = 0;
                    for (int nextColumn = column; nextColumn < line; nextColumn++) {
                        sum += matrixLower[line][nextColumn] * invertedMatrix[nextColumn][column];
                    }
                    invertedMatrix[line][column] = -sum / matrixLower[line][line];
                }
            }
        }
        return invertedMatrix;
    }

    public static double[][] invertUpper(double[][] matrixUpper) {
        int size = matrixUpper.length;
        double[][] invertedMatrix = new double[size][size];

        for (int column = size - 1; column >= 0; column--) {
            for (int line = column; line >= 0; line--) {
                if (line == column)
                    invertedMatrix[line][column] = 1;
                else {
                    double sum = 0;
                    for (int nextLine = line; nextLine <= column; nextLine++) {
                        sum += matrixUpper[line][nextLine] * invertedMatrix[nextLine][column];
                    }
                    invertedMatrix[line][column] = -sum;
                }
            }
        }
        return invertedMatrix;
    }

    public static double[][] daysToDeathMatrixN(double[][] matrixQ) {
        double[][] matrixIdentity = new double[matrixQ.length][matrixQ[0].length];
        for (int order = 0; order < matrixIdentity.length; order++)
            matrixIdentity[order][order] = 1;
        return invertMatrix(subtractMatrices(matrixIdentity, matrixQ));
    }

    public static double[][] daysToDeathMatrixM(double[][] matrixN) {
        double[][] matrix1 = new double[1][matrixN[0].length];
        for (int index = 0; index < matrix1[0].length; index++)
            matrix1[0][index] = 1;
        return multiplyMatrices(matrix1, matrixN);

    }

    public static double[][] invertMatrix(double[][] matrix) {
        double[][][] luDecomposition = luDecomposition(matrix);
        double[][] inverseUpper = invertUpper(luDecomposition[0]), inverseLower = invertLower(luDecomposition[1]);
        return multiplyMatrices(inverseUpper, inverseLower);
    }
}