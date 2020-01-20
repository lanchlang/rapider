/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// Synchronized version numbers for dependencies used by (some) modules
private object Versions {
    const val kotlin = "1.3.61"
    const val coroutines = "1.3.3"
    const val androidx_core="1.2.0-beta01"
    const val androidx_appcompat = "1.1.0"
    const val androidx_constraintlayout = "2.0.0-beta4"
    const val androidx_preference = "1.0.0"
    const val navigation="2.2.0-beta01"
    const val workmanager = "2.2.0"
    const val google_material = "1.1.0-beta01"

    const val dagger ="2.21"

    const val retrofit="2.6.0"

    const val okhttp="3.13.1"

    const val android_state="1.4.1"

    const val elevationimageview="2.4"   //带有阴影的image view
    //弹出框
    const val base_popup="2.2.1"
    const val android_gradle_plugin = "3.4.1"

    const val mozilla_android_components ="+" // Always use the latest snapshot

    const val thirdparty_sentry = "1.7.10"

    const val espresso_core = "3.1.0"
    const val espresso_version = "3.1.0"
    const val mockwebserver = "3.10.0"
    const val orchestrator = "1.1.1"
    const val tools_test_rules = "1.1.0"
    const val tools_test_runner = "1.1.0"
    const val uiautomator = "2.2.0"
    const val leakcanary="1.6.3"

    const val gson="2.8.6"

    const val switchbutton="2.0.0"

    const val browser="1.0.0"

    const val immersionbar="3.0.0"
}

// Synchronized dependencies used by (some) modules
object Deps {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlin_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val androidx_core_ktx = "androidx.core:core-ktx:${Versions.androidx_core}"

    const val mozilla_concept_engine = "org.mozilla.components:concept-engine:${Versions.mozilla_android_components}"
    const val mozilla_concept_tabstray = "org.mozilla.components:concept-tabstray:${Versions.mozilla_android_components}"
    const val mozilla_concept_toolbar = "org.mozilla.components:concept-toolbar:${Versions.mozilla_android_components}"
    const val mozilla_concept_storage = "org.mozilla.components:concept-storage:${Versions.mozilla_android_components}"
    const val mozilla_concept_sync = "org.mozilla.components:concept-sync:${Versions.mozilla_android_components}"
    const val mozilla_concept_fetch = "org.mozilla.components:concept-fetch:${Versions.mozilla_android_components}"
    const val mozilla_concept_awesomebar = "org.mozilla.components:concept-awesomebar:${Versions.mozilla_android_components}"

    const val mozilla_browser_awesomebar = "org.mozilla.components:browser-awesomebar:${Versions.mozilla_android_components}"
    const val mozilla_browser_engine_gecko = "org.mozilla.components:browser-engine-gecko:${Versions.mozilla_android_components}"
    const val mozilla_browser_engine_gecko_beta = "org.mozilla.components:browser-engine-gecko-beta:${Versions.mozilla_android_components}"
    const val mozilla_browser_engine_gecko_nightly = "org.mozilla.components:browser-engine-gecko-nightly:${Versions.mozilla_android_components}"
    const val mozilla_browser_domains = "org.mozilla.components:browser-domains:${Versions.mozilla_android_components}"
    const val mozilla_browser_search = "org.mozilla.components:browser-search:${Versions.mozilla_android_components}"
    const val mozilla_browser_session = "org.mozilla.components:browser-session:${Versions.mozilla_android_components}"
    const val mozilla_browser_state = "org.mozilla.components:browser-state:${Versions.mozilla_android_components}"
    const val mozilla_browser_tabstray = "org.mozilla.components:browser-tabstray:${Versions.mozilla_android_components}"
    const val mozilla_browser_toolbar = "org.mozilla.components:browser-toolbar:${Versions.mozilla_android_components}"
    const val mozilla_browser_menu = "org.mozilla.components:browser-menu:${Versions.mozilla_android_components}"
    const val mozilla_browser_errorpages = "org.mozilla.components:browser-errorpages:${Versions.mozilla_android_components}"
    const val mozilla_browser_storage_sync = "org.mozilla.components:browser-storage-sync:${Versions.mozilla_android_components}"
    const val mozilla_browser_icons = "org.mozilla.components:browser-icons:${Versions.mozilla_android_components}"

    const val mozilla_feature_accounts = "org.mozilla.components:feature-accounts:${Versions.mozilla_android_components}"
    const val mozilla_feature_awesomebar = "org.mozilla.components:feature-awesomebar:${Versions.mozilla_android_components}"
    const val mozilla_feature_contextmenu = "org.mozilla.components:feature-contextmenu:${Versions.mozilla_android_components}"
    const val mozilla_feature_customtabs = "org.mozilla.components:feature-customtabs:${Versions.mozilla_android_components}"
    const val mozilla_feature_findinpage = "org.mozilla.components:feature-findinpage:${Versions.mozilla_android_components}"
    const val mozilla_feature_media = "org.mozilla.components:feature-media:${Versions.mozilla_android_components}"
    const val mozilla_feature_sitepermissions = "org.mozilla.components:feature-sitepermissions:${Versions.mozilla_android_components}"
    const val mozilla_feature_intent = "org.mozilla.components:feature-intent:${Versions.mozilla_android_components}"
    const val mozilla_feature_search = "org.mozilla.components:feature-search:${Versions.mozilla_android_components}"
    const val mozilla_feature_session = "org.mozilla.components:feature-session:${Versions.mozilla_android_components}"
    const val mozilla_feature_toolbar = "org.mozilla.components:feature-toolbar:${Versions.mozilla_android_components}"
    const val mozilla_feature_tabs = "org.mozilla.components:feature-tabs:${Versions.mozilla_android_components}"
    const val mozilla_feature_downloads = "org.mozilla.components:feature-downloads:${Versions.mozilla_android_components}"
    const val mozilla_feature_storage = "org.mozilla.components:feature-storage:${Versions.mozilla_android_components}"
    const val mozilla_feature_prompts = "org.mozilla.components:feature-prompts:${Versions.mozilla_android_components}"
    const val mozilla_feature_pwa = "org.mozilla.components:feature-pwa:${Versions.mozilla_android_components}"
    const val mozilla_feature_qr = "org.mozilla.components:feature-qr:${Versions.mozilla_android_components}"
    const val mozilla_feature_readerview = "org.mozilla.components:feature-readerview:${Versions.mozilla_android_components}"
    const val mozilla_feature_webcompat= "org.mozilla.components:feature-webcompat:${Versions.mozilla_android_components}"
    const val mozilla_feature_webnotifications = "org.mozilla.components:feature-webnotifications:${Versions.mozilla_android_components}"

    const val mozilla_ui_autocomplete = "org.mozilla.components:ui-autocomplete:${Versions.mozilla_android_components}"
    const val mozilla_ui_colors = "org.mozilla.components:ui-colors:${Versions.mozilla_android_components}"

    const val mozilla_service_firefox_accounts = "org.mozilla.components:service-firefox-accounts:${Versions.mozilla_android_components}"
    const val mozilla_service_glean = "org.mozilla.components:service-glean:${Versions.mozilla_android_components}"
    const val mozilla_service_experiments = "org.mozilla.components:service-experiments:${Versions.mozilla_android_components}"

    const val mozilla_support_utils = "org.mozilla.components:support-utils:${Versions.mozilla_android_components}"
    const val mozilla_support_ktx= "org.mozilla.components:support-ktx:${Versions.mozilla_android_components}"
    const val mozilla_support_rustlog = "org.mozilla.components:support-rustlog:${Versions.mozilla_android_components}"
    const val mozilla_support_rusthttp = "org.mozilla.components:support-rusthttp:${Versions.mozilla_android_components}"

    const val mozilla_lib_crash = "org.mozilla.components:lib-crash:${Versions.mozilla_android_components}"

    const val thirdparty_sentry = "io.sentry:sentry-android:${Versions.thirdparty_sentry}"

    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"
    const val androidx_preference = "androidx.preference:preference-ktx:${Versions.androidx_preference}"
    const val androidx_work_runtime = "androidx.work:work-runtime-ktx:${Versions.workmanager}"
    const val androidx_navigation="androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val androidx_navigation_ui="androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val androidx_navigation_arg_plugin="androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    const val google_material = "com.google.android.material:material:${Versions.google_material}"

    const val tools_androidgradle = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val tools_kotlingradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso_version}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    const val espresso_idling_resources = "androidx.test.espresso:espresso-idling-resource:${Versions.espresso_version}"
    const val espresso_web = "androidx.test.espresso:espresso-web:${Versions.espresso_version}"
    const val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.mockwebserver}"
    const val orchestrator =  "androidx.test:orchestrator:${Versions.orchestrator}"
    const val tools_test_rules = "androidx.test:rules:${Versions.tools_test_rules}"
    const val tools_test_runner = "androidx.test:runner:${Versions.tools_test_runner}"
    const val uiautomator = "androidx.test.uiautomator:uiautomator:${Versions.uiautomator}"

    const val dagger="com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler="com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val retrofit2="com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit2_gson_converter="com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val okhttp="com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttp_logging_interceptor="com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttp_dnsoverhttps="com.squareup.okhttp3:okhttp-dnsoverhttps:${Versions.okhttp}"

    const val android_state="com.evernote:android-state:${Versions.android_state}"
    const val android_state_compiler="com.evernote:android-state-processor:${Versions.android_state}"

    const val elevationimageview="com.qhutch.elevationimageview:elevationimageview:${Versions.elevationimageview}"

    const val base_popup="com.github.razerdp:BasePopup:${Versions.base_popup}"
    const val base_popup_androidx="com.github.razerdp:BasePopup-compat-androidx:${Versions.base_popup}"

    const val leakcanary="com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    const val leakcanary_op="com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakcanary}"

    const val gson="com.google.code.gson:gson:${Versions.gson}"
    const val switchbutton="com.kyleduo.switchbutton:library:${Versions.switchbutton}"
    const val browser="androidx.browser:browser:${Versions.browser}"

    const val immersionbar="com.gyf.immersionbar:immersionbar:${Versions.immersionbar}"
    const val immersionbar_components="com.gyf.immersionbar:immersionbar-components:${Versions.immersionbar}"
    const val immersionbar_ktx="com.gyf.immersionbar:immersionbar-ktx:${Versions.immersionbar}"


}
