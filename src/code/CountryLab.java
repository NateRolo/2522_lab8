import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class CountryLab
{
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
    }

    private static Stream<String> filteredStream(final List<String> list)
    {
        final Stream<String> filteredStream;

        filteredStream = list.stream()
                .filter(Objects::nonNull)
                .filter(str->!str.isBlank());

        return filteredStream;
    }

    private static void writeLongCountryNames(final List<String> countriesList,
                                              final Path dataPath) throws IOException
    {
        final List<String> longCountryNames;

        longCountryNames = filteredStream(countriesList)
                .filter(str->str.length()>10)
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "-----Country names longer than 10 characters-----\n",
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

    private static void writeShortCountryNames(final List<String> countriesList,
                                               final Path dataPath) throws IOException
    {
        final List<String> shortCountryNames;

        shortCountryNames = filteredStream(countriesList)
                .filter(str->str.length()<5)
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

    private static void writeCountriesStartingWithA(final List<String> countriesList,
                                                    final Path dataPath) throws IOException
    {
        final List<String> startsWithA = filteredStream(countriesList)
                .filter(str->str.startsWith("A"))
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n-----Country names that start with \"A\"-----\n",
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

    private static void writeCountriesEndingWithLand(final List<String> countriesList,
                                                     final Path dataPath) throws IOException
    {
        final List<String> endsWithLand = filteredStream(countriesList)
                .filter(str->
                    str.length() > 4 &&
                            str.substring(str.length() - 4)
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

    private static void writeCountriesThatContainUnited(final List<String> countriesList,
                                                        final Path dataPath) throws IOException
    {
        final List<String> containsUnited = filteredStream(countriesList)
                .filter(str->str.contains("United"))
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

    private static void writeCountriesInAscendingOrder(final List<String> countriesList,
                                                       final Path dataPath) throws IOException
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

    private static void writeUniqueFirstLetters(final List<String> countriesList,
                                                final Path dataPath)
    {
        final List<String> uniqueFirstLetters = filteredStream(countriesList)
                .map(str->str.substring(0, 1))
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

    private static void writeCountOfCountries(final List<String> countriesList,
                                              final Path dataPath)
    {
        final long countOfCountries = filteredStream(countriesList)
                .count();

        try
        {
            Files.writeString(dataPath,
                              "\n------Count of countries-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.writeString(dataPath,
                        countOfCountries + "\n",
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    // add both if equal to maximum
    private static void writeLongestCountryName(final List<String> countriesList,
                                              final Path dataPath)
    {
        final Optional<String> longestCountryName = filteredStream(countriesList)
                .max(Comparator.comparingInt(String::length));

        try
        {
            Files.writeString(dataPath,
                              "\n------Longest country name-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.writeString(dataPath,
                              longestCountryName.toString(),
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    // add both if equal to minimum?
    private static void writeShortestCountryName(final List<String> countriesList,
                                                final Path dataPath)
    {
        final Optional<String> shortestCountryName = filteredStream(countriesList)
                .min(Comparator.comparingInt(String::length));

        try
        {
            Files.writeString(dataPath,
                              "\n------Shortest country name-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.writeString(dataPath,
                              shortestCountryName.toString(),
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void writeCountriesInUpper(final List<String> countriesList,
                                              final Path dataPath)
    {
        final List<String> countriesInUpper = filteredStream(countriesList)
                .map(str->str.toUpperCase())
                .toList();
        try
        {
            Files.writeString(dataPath,
                              "\n------Countries in uppercase-----\n",
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
            Files.writeString(dataPath,
                              countriesInUpper.toString(),
                              StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }


    private static void writeCountriesWithMoreThanOneWord(final List<String> countriesList,
                                                          final Path dataPath)
    {
        //step 13
    }

    private static void writeCountriesToCharacterCount(final List<String> countriesList,
                                                       final Path dataPath)
    {
        final List<String> countriesWithCharacterCount = filteredStream(countriesList)
                .map(str->str + ": " + str.length())
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






}

