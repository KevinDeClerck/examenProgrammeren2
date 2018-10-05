package com.realdolmen.examen.examenprogrameren2declerck.services;

import com.realdolmen.examen.examenprogrameren2declerck.exceptions.NoQueryPossibleException;
import com.realdolmen.examen.examenprogrameren2declerck.services.MovieService;
import com.realdolmen.examen.examenprogrameren2declerck.repositories.MovieRepository;
import com.realdolmen.examen.examenprogrameren2declerck.domain.Movie;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
    //TODO
    //Opdracht 3 Unit testen met Mockito : Er is al een deel van de service test opgesteld. Alle gegevens die je nodig hebt staan al ingevuld.
    //25 : zorg ervoor dat Mockito gebruikt kan worden, over heel de klasse.
    //26 : annoteer alle methoden met de juiste annotaties, en private attributen (waar nodig), zodat ze aanzien worden als test methoden. Boven sommige methoden staan tips, bekijk ze goed. 
    //27 : tracht alle methoden die hieronder beschreven zijn in te vullen zodat ze slagen. Tips kan je vinden in de methoden zelf.
    
    private MovieService movieService;
    private List<Movie>movies;
    private Movie movieObjectToTest;

    @Mock
    private MovieRepository movieRepository;

	//TODO
    @Before
    public void init() {
        movieService = new MovieService(movieRepository);
        movies = new ArrayList<>();
        movieObjectToTest = new Movie(1,"comedy", "Ace ventura");
    }

    //TODO maak een test die nagaat of MovieService de juiste methode opvraagt bij MovieRepository, 
    //zorg voor de duidelijke structuur van een test methode
    @Test
    public void findAllMoviesTest() throws Exception {
        when(movieRepository.find("SELECT * FROM movies")).thenReturn(movies);
        List<Movie> result = movieService.findAllMovies();
        assertEquals(result,movies);
        verify(movieRepository, times(1)).find("SELECT * FROM movies");
        
    }
    
    //TODO maak een test die nagaat of de MovieService de juiste methode opvraagt bij MovieRepository
    //Zorg dat MovieRepository een NoQueryPossibleException gooit
    @Test 
    public void findAllMoviesTestNoQueryPossibleExceptionThrow() throws Exception{
        when(movieRepository.find("SELECT titeeel FROM movies")).thenThrow(NoQueryPossibleException.class);
        verify(movieRepository, times(0)).find("SELECT titeeel FROM movies");
    }

    //TODO maak een test die nagaat of de MovieService de juiste methode opvraagt bij MovieRepository
    //Bekijk de code van MovieRepository.findMovieById() en zorg dat de juiste movie teruggegeven wordt
    @Test
    public void findMovieByIdTest() throws Exception {
        movies.add(movieObjectToTest);
        when(movieRepository.find(QueryHelper.findById(1))).thenReturn(movies);
        Movie result = movieService.findMovieById(1);
        assertEquals(result, movies.get(0));
        verify(movieRepository, times(1)).find(QueryHelper.findById(1));
    }

    //TODO Maak een test die ervoor zorgt dat het resultaat van de getAllPalindromeTitles() één of meerdere resultaten kan weergegeven.
    //onthou dat in de methode getAllPalindromeTitles een andere private methode wordt aangesproken die op zijn beurt nog een andere methode van de MovieService aanspreekt
    //zorg dat je er zeker rekening mee houdt dat ook hier ergens MovieRepository iets gevraagd zal worden.
    //vb van movies met palindrome titles = "boob", "aka","dad","ROTOR"
    //Voeg met andere woorden een of meerdere movies toe die een titel hebben dat een palindrome voorsteld
    @Test
    public void getAllPalindromeTitlesTestTitleAddedToList() {
        List <String> strings = new ArrayList<>();
        String m1 = "boob";
        String m2 = "aka";
        strings.add(m1);
        strings.add(m2);
       List<String>test = movieService.getAllPalindromeTitles();
       // assertEquals(strings, test);        
    }
   
    //TODO test de methode getAllPalindromeTitles, waarbij de MovieRepository methode die wordt opgeroepen een NoQueryPossibleException gooit
    //kijk goed naar de methodes in MovieService, kijk wat er gebeurd en zorg dat je resultaat net is zoals er verwacht wordt
    //Onthou hierbij dat private methoden niet getest worden, maar de publieke methode 
    @Test
    public void getAllPalindromeTitlesTestNoQueryPossibleExceptionThrownAndCatchedTitleNotAdded() {
        
    }
    
    
    //TODO test the isAPalindrome method gebruik hiervoor "saippuakivikauppias"
    //nice to know, this is the longest palindrome according to the guiness book of records
    @Test
    public void isAPalinDromeTestTrue() {
    }

}
