import org.gradle.api.JavaVersion
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.io.File
import java.io.FileInputStream
import java.util.*

object ConfigData {
    //Default
    const val compileSdk = 33
    const val applicationId = "com.iamkurtgoz.cryptocurrencyapi"
    const val minSdk = 24
    const val targetSdk = 33
    const val versionName = "0.0.1"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"

    //Proguard
    const val minifyEnabledDebug = false
    const val minifyEnabledRelease = true

    object CompileOptions {
        val javaSourceCompatibility = JavaVersion.VERSION_17
        val kotlinJvmTarget: String = JavaVersion.VERSION_17.toString()
        val languageVersion = "1.8"
    }

    /**
     * computedVersionCode()
     * do not name this to getVersionCode. getVersionCode conflicts with the automatic getter of versionCode
     * version code is an int a value between 0 and max int value 2147483647 is expected.
     * This function returns at int in yyyMMddHH format
     * For example, 2022061121 for 11 June 2022 between 21:00 to 21:59
     * This gives a new versioncode for every different hour of day and same code within same hour of hour of day
     * Max int value is 2147483647. So after year 2147 it will overflow to -ve values.
     * max value in year 2147 will be 2147121223 so Lot of scope of manually incrementing up-to 2147483647  will be there.
     * @return an int corresponding to current hour in yyyyMMddHH format
     */
    fun computedVersionCode(): Int {
        val date = Date()
        val formattedDate = SimpleDateFormat("yyyyMMddHH", Locale.getDefault()).format(date)
        return formattedDate.toLong().toInt()
    }

}