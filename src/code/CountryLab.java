import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;


/**
 * This program processes a list of country names from a text file and
 * performs various operations, such as filtering, sorting, and analyzing
 * country names, and writes the results to an output file.
 *
 * @author Valley B
 * @author Nathan O
 * @version 1.0 2025
 */
public class CountryLab
{
    private static final int LONG_COUNTRY_NAME_MIN_LENGTH  = 10;
    private static final int SHORT_COUNTRY_NAME_MAX_LENGTH        = 5;
    private static final int COUNTRY_ENDS_WITH_LAND_MIN_LENGTH      = 4;
    private static final int COUNTRY_ENDS_WITH_LAND_INDEX_OFFSET = 4;
    private static final int COUNTRY_NAMES_MIN_LENGTH_3       = 3;

    /**
     * Main entry point for the program. Reads country names from a file,
     * ensures output directories exist, and calls methods to process
     * and write filtered data.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an error occurs while reading or writing files.
     */
    public static void main(final String[] args) throws IOException
    {
        final Path countriesPath;
        final Path matchesPath;
        final Path dataPath;

        final List<String> countriesList;

        countriesPath = Paths.get("src", "res", "week8countries.txt");
        if(Files.notExists(countriesPath))
        {
            throw new IOException("File not found.");
        }

        matchesPath = Paths.get("src", "res", "matches");
        if(Files.notExists(matchesPath))
        {
            Files.createDirectory(matchesPath);
        }

        dataPath = Paths.get("src", "res", "matches", "data.txt");
        if(Files.notExists(dataPath))
        {
            Files.createFile(dataPath);
        }

        countriesList = Files.readAllLines(countriesPath);

        writeLongCountryNames(countriesList, dataPath);
        writeShortCountryNames(countriesList, dataPath);
        writeCountriesStartingWithA(countriesList,
                                    dataPath);
        writeCountriesEndingWithLand(countriesList,
                                     dataPath);
        writeCountriesThatContainUnited(countriesList,
                                        dataPath);
        writeCountriesInAscendingOrder(countriesList,
                                       dataPath);
        writeCountriesInDescendingOrder(countriesList,
                                        dataPath);
        writeUniqueFirstLetters(countriesList,
                                dataPath);
        writeCountOfCountries(countriesList,
                              dataPath);
        writeLongestCountryName(countriesList,
                                dataPath);
        writeShortestCountryName(countriesList,
                                dataPath);
        writeCountriesInUpper(countriesList,
                                dataPath);
        writeCountriesToCharacterCount(countriesList,
                                dataPath);
        writeCountriesWithMoreThanOneWord(countriesList,
                                          dataPath);
        writeTrueIfNameStartsWithZ(countriesList,
                                   dataPath);
        writeAllNamesLongerThan3(countriesList,
                                 dataPath);
    }

    /*
     * Filters a list of country names by removing null and blank entries.
     *
     * @param list List of country names.
     * @return A stream of filtered country names.
     */
    private static Stream<String> filteredStream(final List<String> list)
    {
        final Stream<String> filteredStream;

        filteredStream = list.stream()
                .filter(Objects::nonNull)
                .filter(country->!country.isBlank());

        return filteredStream;
    }

    /*
     * Writes country names longer than 10 characters to the output file.
     */
    private static void writeLongCountryNames(final List<String> countriesList,
                                              final Path dataPath)
    {
        final List<String> longCountryNames;

        longCountryNames = filteredStream(countriesList)
                .filter(country-> country.length() > LONG_COUNTRY_NAME_MIN_LENGTH)
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "Country names longer than 10 characters:\n",
                              StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(dataPath,
                        longCountryNames,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes country names shorter than 5 characters to the output file.
     */
    private static void writeShortCountryNames(final List<String> countriesList,
                                               final Path dataPath)
    {
        final List<String> shortCountryNames;

        shortCountryNames = filteredStream(countriesList)
                .filter(country-> country.length() < SHORT_COUNTRY_NAME_MAX_LENGTH)
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n-----Country names shorter than 5 characters-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.write(dataPath,
                        shortCountryNames,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes country names that start with 'A' to the output file.
     */
    private static void writeCountriesStartingWithA(final List<String> countriesList,
                                                    final Path dataPath)
    {
        final List<String> startsWithA = filteredStream(countriesList)
                .filter(country->country.startsWith("A"))
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\nCountry names starting with 'A':\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.write(dataPath,
                        startsWithA,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes country names that end with "land" to the output file.
     */
    private static void writeCountriesEndingWithLand(final List<String> countriesList,
                                                     final Path dataPath)
    {
        final List<String> endsWithLand = filteredStream(countriesList)
                .filter(country->
                    country.length() > COUNTRY_ENDS_WITH_LAND_MIN_LENGTH &&
                            country.substring(country.length() - 
                                              COUNTRY_ENDS_WITH_LAND_INDEX_OFFSET)
                                    .equalsIgnoreCase("land")
                        )
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n-----Country that end with \"land\"-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.write(dataPath,
                        endsWithLand,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes country names containing "United" to the output file.
     */
    private static void writeCountriesThatContainUnited(final List<String> countriesList,
                                                        final Path dataPath)
    {
        final List<String> containsUnited = filteredStream(countriesList)
                .filter(country->country.contains("United"))
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n-----Countries that contain \"United\"-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.write(dataPath,
                        containsUnited,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes country names in ascending order to the output file.
     */
    private static void writeCountriesInAscendingOrder(final List<String> countriesList,
                                                       final Path dataPath)
    {
        final List<String> ascendingOrder = filteredStream(countriesList)
                .sorted()
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n------Countries in ascending order-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.write(dataPath,
                        ascendingOrder,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes country names in descending order to the output file.
     */
    private static void writeCountriesInDescendingOrder(final List<String> countriesList,
                                                        final Path dataPath) throws IOException
    {
        final List<String> descendingOrder = filteredStream(countriesList)
                .sorted(Comparator.comparing(String::toString).reversed())
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n------Countries in descending order-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.write(dataPath,
                        descendingOrder,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes unique first letters of country names to the output file.
     */
    private static void writeUniqueFirstLetters(final List<String> countriesList,
                                                final Path dataPath)
    {
        final List<String> uniqueFirstLetters = filteredStream(countriesList)
                .map(country->country.substring(0, 1))
                .distinct()
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n------Unique first letters of countries-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.write(dataPath,
                        uniqueFirstLetters,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes the total count of countries to the output file.
     */
    private static void writeCountOfCountries(final List<String> countriesList,
                                              final Path dataPath)
    {
        final long countOfCountries = filteredStream(countriesList)
                .count();

        try
        {
            Files.writeString(dataPath,
                              "\n------Count of countries-----\n" +
                              countOfCountries,
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes the longest country name(s) to the output file.
     */
    private static void writeLongestCountryName(final List<String> countriesList,
                                              final Path dataPath)
    {
        final int longestCountryNameLength = filteredStream(countriesList)
                .mapToInt(String::length)
                .max()
                .getAsInt();

        final List<String> longestCountryName = filteredStream(countriesList)
                .filter(country->country.length() == longestCountryNameLength)
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n------Longest country names-----\n" +
                              longestCountryName,
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes the shortest country name(s) to the output file.
     */
    private static void writeShortestCountryName(final List<String> countriesList,
                                                final Path dataPath)
    {
        final int shortestCountryNameLength = filteredStream(countriesList)
                .mapToInt(String::length)
                .min()
                .getAsInt();

        final List<String> longestCountryName = filteredStream(countriesList)
                .filter(country->country.length() == shortestCountryNameLength)
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n------Shortest country names-----\n" +
                              longestCountryName,
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes country names in uppercase to the output file.
     */
    private static void writeCountriesInUpper(final List<String> countriesList,
                                              final Path dataPath)
    {
        final List<String> countriesInUpper = filteredStream(countriesList)
                .map(String::toUpperCase)
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n------Countries in uppercase-----\n" +
                              countriesInUpper,
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes country names that have more than one word to the output file.
     */
    private static void writeCountriesWithMoreThanOneWord(final List<String> countriesList,
                                                          final Path dataPath)
    {
        final List<String> countriesWithMoreThanOneWord = filteredStream(countriesList)
                .filter(country->country.trim().contains(" "))
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n------Countries with more than one word------\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.write(dataPath,
                        countriesWithMoreThanOneWord,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes country names along with their character counts to the output file.
     */
    private static void writeCountriesToCharacterCount(final List<String> countriesList,
                                                       final Path dataPath)
    {
        final List<String> countriesWithCharacterCount = filteredStream(countriesList)
                .map(country->country + ": " + country.length())
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n------Countries with their character counts-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.write(dataPath,
                        countriesWithCharacterCount,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Writes whether there is a country name that starts with 'Z' to the file.
     */
    private static void writeTrueIfNameStartsWithZ(final List<String> countriesList,
                                                   final Path dataPath)
    {
        final boolean countryStartsWithZ = filteredStream(countriesList)
                .anyMatch(country->country.startsWith("Z"));

        try
        {
            Files.writeString(dataPath,
                              "\n------Country starts with Z-----\n" +
                              countryStartsWithZ,
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    /*
     * Writes whether all country names are longer than 3 characters to the file.
     */
    private static void writeAllNamesLongerThan3(final List<String> countriesList,
                                                 final Path dataPath)
    {
        final boolean allNamesLongerThan3 = filteredStream(countriesList)
                .allMatch(country->country.length()>COUNTRY_NAMES_MIN_LENGTH_3);

        try
        {
            Files.writeString(dataPath,
                              "\n-----All countries longer than 3------\n" +
                              allNamesLongerThan3,
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}

