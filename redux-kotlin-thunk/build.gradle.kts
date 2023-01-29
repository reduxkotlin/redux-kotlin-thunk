import util.jvmCommonTest

plugins {
    id("convention.library-mpp-all")
    id("convention.publishing-mpp")
}

android {
    namespace = "org.reduxkotlin.thunk"
}

kotlin {
    sourceSets {
        jvmCommonTest {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:_")
            }
        }
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api("org.reduxkotlin:redux-kotlin:_")
            }
        }
    }
}
