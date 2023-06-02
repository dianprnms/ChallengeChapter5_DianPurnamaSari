import com.example.challengechapter5_dianpurnamasari.model.ResponseData
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ResponseDataTest {

    private lateinit var responseData: ResponseData

    @Before
    fun setUp() {
        val adult = false
        val backdropPath = "path/to/backdrop.jpg"
        val budget = 10000000
        val homepage = "https://example.com"
        val id = 123
        val imdbId = "tt1234567"
        val originalLanguage = "en"
        val originalTitle = "Original Title"
        val overview = "Movie overview"
        val popularity = 7.8
        val posterPath = "path/to/poster.jpg"
        val releaseDate = "2023-05-31"
        val revenue = 50000000
        val runtime = 120
        val status = "Released"
        val tagline = "Movie tagline"
        val title = "Title"
        val video = false
        val voteAverage = 6.5
        val voteCount = 1000

        responseData = ResponseData(adult, backdropPath, budget, homepage, id, imdbId, originalLanguage,
            originalTitle, overview, popularity, posterPath, releaseDate, revenue, runtime, status, tagline,
            title, video, voteAverage, voteCount)
    }

    @After
    fun tearDown() {
        // Cleanup code, if any
    }


    // Pengujian 1 = pengujian persamaan objek di dalam responseData dengan model ResponseData
    @Test
    fun testConstructor() {
        val responseData = ResponseData(
            false, "path/to/backdrop.jpg", 10000000, "https://example.com", 123,
            "tt1234567", "en", "Original Title", "Movie overview", 7.8, "path/to/poster.jpg", "2023-05-31",
            50000000, 120, "Released", "Movie tagline", "Title", false, 6.5, 1000
        )

        assertEquals(false, responseData.adult)
        assertEquals("path/to/backdrop.jpg", responseData.backdropPath)
        assertEquals(10000000, responseData.budget)
        assertEquals("https://example.com", responseData.homepage)
        assertEquals(123, responseData.id)
        assertEquals("tt1234567", responseData.imdbId)
        assertEquals("en", responseData.originalLanguage)
        assertEquals("Original Title", responseData.originalTitle)
        assertEquals("Movie overview", responseData.overview)
        assertEquals(7.8, responseData.popularity, 0.0)
        assertEquals("path/to/poster.jpg", responseData.posterPath)
        assertEquals("2023-05-31", responseData.releaseDate)
        assertEquals(50000000, responseData.revenue)
        assertEquals(120, responseData.runtime)
        assertEquals("Released", responseData.status)
        assertEquals("Movie tagline", responseData.tagline)
        assertEquals("Title", responseData.title)
        assertEquals(false, responseData.video)
        assertEquals(6.5, responseData.voteAverage, 0.0)
        assertEquals(1000, responseData.voteCount)
    }

    // Pengujian 2 =
    @Test
    fun testEquals_SameObject() {
        val responseData = ResponseData(
            false, "path/to/backdrop.jpg", 10000000, "https://example.com", 123,
            "tt1234567", "en", "Original Title", "Movie overview", 7.8, "path/to/poster.jpg", "2023-05-31",
            50000000, 120, "Released", "Movie tagline", "Title", false, 6.5, 1000
        )

        assertEquals(true, responseData == responseData)
    }

    // Pengujian 3
    @Test
    fun testEquals_DifferentObjects_SameValues() {
        val responseData1 = ResponseData(
            false, "path/to/backdrop.jpg", 10000000, "https://example.com", 123,
            "tt1234567", "en", "Original Title", "Movie overview", 7.8, "path/to/poster.jpg", "2023-05-31",
            50000000, 120, "Released", "Movie tagline", "Title", false, 6.5, 1000
        )

        val responseData2 = ResponseData(
            false, "path/to/backdrop.jpg", 10000000, "https://example.com", 123,
            "tt1234567", "en", "Original Title", "Movie overview", 7.8, "path/to/poster.jpg", "2023-05-31",
            50000000, 120, "Released", "Movie tagline", "Title", false, 6.5, 1000
        )

        assertEquals(true, responseData1 == responseData2)
    }

    // Pengujian 4
    @Test
    fun testEquals_DifferentObjects_DifferentValues() {
        val responseData1 = ResponseData(
            false, "path/to/backdrop.jpg", 10000000, "https://example.com", 123,
            "tt1234567", "en", "Original Title", "Movie overview", 7.8, "path/to/poster.jpg", "2023-05-31",
            50000000, 120, "Released", "Movie tagline", "Title", false, 6.5, 1000
        )

        val responseData2 = ResponseData(
            true, "different/backdrop.jpg", 20000000, "https://example2.com", 456,
            "tt9876543", "es", "Different Title", "Different overview", 6.5, "different/poster.jpg", "2023-06-01",
            60000000, 90, "Not Released", "Different tagline", "Different Title", true, 8.0, 500
        )

        assertEquals(false, responseData1 == responseData2)
    }

    // Pengujian 5
    @Test
    fun testHashCode() {
        val responseData1 = ResponseData(
            false, "path/to/backdrop.jpg", 10000000, "https://example.com", 123,
            "tt1234567", "en", "Original Title", "Movie overview", 7.8, "path/to/poster.jpg", "2023-05-31",
            50000000, 120, "Released", "Movie tagline", "Title", false, 6.5, 1000
        )

        val responseData2 = ResponseData(
            false, "path/to/backdrop.jpg", 10000000, "https://example.com", 123,
            "tt1234567", "en", "Original Title", "Movie overview", 7.8, "path/to/poster.jpg", "2023-05-31",
            50000000, 120, "Released", "Movie tagline", "Title", false, 6.5, 1000
        )

        assertEquals(true, responseData1.hashCode() == responseData2.hashCode())
    }

    // Pengujian 6
    @Test
    fun testToString() {
        val responseData = ResponseData(
            false, "path/to/backdrop.jpg", 10000000, "https://example.com", 123,
            "tt1234567", "en", "Original Title", "Movie overview", 7.8, "path/to/poster.jpg", "2023-05-31",
            50000000, 120, "Released", "Movie tagline", "Title", false, 6.5, 1000
        )

        val expectedToString = "ResponseData(adult=false, backdropPath=path/to/backdrop.jpg, budget=10000000, homepage=https://example.com, id=123, imdbId=tt1234567, originalLanguage=en, originalTitle=Original Title, overview=Movie overview, popularity=7.8, posterPath=path/to/poster.jpg, releaseDate=2023-05-31, revenue=50000000, runtime=120, status=Released, tagline=Movie tagline, title=Title, video=false, voteAverage=6.5, voteCount=1000)"

        assertEquals(expectedToString, responseData.toString())
    }

    // Pengujian 7
    @Test
    fun testGetters() {
        val responseData = ResponseData(
            false, "path/to/backdrop.jpg", 10000000, "https://example.com", 123,
            "tt1234567", "en", "Original Title", "Movie overview", 7.8, "path/to/poster.jpg", "2023-05-31",
            50000000, 120, "Released", "Movie tagline", "Title", false, 6.5, 1000
        )

        assertEquals(false, responseData.adult)
        assertEquals("path/to/backdrop.jpg", responseData.backdropPath)
        assertEquals(10000000, responseData.budget)
        assertEquals("https://example.com", responseData.homepage)
        assertEquals(123, responseData.id)
        assertEquals("tt1234567", responseData.imdbId)
        assertEquals("en", responseData.originalLanguage)
        assertEquals("Original Title", responseData.originalTitle)
        assertEquals("Movie overview", responseData.overview)
        assertEquals(7.8, responseData.popularity, 0.0)
        assertEquals("path/to/poster.jpg", responseData.posterPath)
        assertEquals("2023-05-31", responseData.releaseDate)
        assertEquals(50000000, responseData.revenue)
        assertEquals(120, responseData.runtime)
        assertEquals("Released", responseData.status)
        assertEquals("Movie tagline", responseData.tagline)
        assertEquals("Title", responseData.title)
        assertEquals(false, responseData.video)
        assertEquals(6.5, responseData.voteAverage, 0.0)
        assertEquals(1000, responseData.voteCount)
    }

    // Pengujian 8
    @Test
    fun testSetters() {
        val responseData = ResponseData(
            false, "path/to/backdrop.jpg", 10000000, "https://example.com", 123,
            "tt1234567", "en", "Original Title", "Movie overview", 7.8, "path/to/poster.jpg", "2023-05-31",
            50000000, 120, "Released", "Movie tagline", "Title", false, 6.5, 1000
        )

        responseData.adult = true
        responseData.backdropPath = "new/path/to/backdrop.jpg"
        responseData.budget = 20000000
        responseData.homepage = "https://example2.com"
        responseData.id = 456
        responseData.imdbId = "tt9876543"
        responseData.originalLanguage = "es"
        responseData.originalTitle = "Different Title"
        responseData.overview = "Different overview"
        responseData.popularity = 6.5
        responseData.posterPath = "new/path/to/poster.jpg"
        responseData.releaseDate = "2023-06-01"
        responseData.revenue = 60000000
        responseData.runtime = 90
        responseData.status = "Not Released"
        responseData.tagline = "Different tagline"
        responseData.title = "Different Title"
        responseData.video = true
        responseData.voteAverage = 8.0
        responseData.voteCount = 500

        assertEquals(true, responseData.adult)
        assertEquals("new/path/to/backdrop.jpg", responseData.backdropPath)
        assertEquals(20000000, responseData.budget)
        assertEquals("https://example2.com", responseData.homepage)
        assertEquals(456, responseData.id)
        assertEquals("tt9876543", responseData.imdbId)
        assertEquals("es", responseData.originalLanguage)
        assertEquals("Different Title", responseData.originalTitle)
        assertEquals("Different overview", responseData.overview)
        assertEquals(6.5, responseData.popularity, 0.0)
        assertEquals("new/path/to/poster.jpg", responseData.posterPath)
        assertEquals("2023-06-01", responseData.releaseDate)
        assertEquals(60000000, responseData.revenue)
        assertEquals(90, responseData.runtime)
        assertEquals("Not Released", responseData.status)
        assertEquals("Different tagline", responseData.tagline)
        assertEquals("Different Title", responseData.title)
        assertEquals(true, responseData.video)
        assertEquals(8.0, responseData.voteAverage, 0.0)
        assertEquals(500, responseData.voteCount)
    }

}
