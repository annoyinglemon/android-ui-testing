val buildVersionCode: () -> Int = {
    System.getenv("FUELED_BUILD_NUMBER")?.toInt() ?: 3
}
val buildVersionName: () -> String = {
    System.getenv("BUILD_VERSION_NAME") ?: "1.1.1"
}

extra.set("buildVersionCode", buildVersionCode)
extra.set("buildVersionName", buildVersionName)
