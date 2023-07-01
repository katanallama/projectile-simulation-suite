{
  jdk17,
  makeWrapper,
  maven,
  nix-gitignore,
}: let
  version = "0.1";
  pname = "pss";
  name = "${pname}-${version}";
  src = nix-gitignore.gitignoreSource ["*.nix"] ./.;

  mavenJdk17 = maven.override {
    jdk = jdk17;
  };

in
  mavenJdk17.buildMavenPackage rec{
    inherit pname name src version;

    mvnHash = "sha256-mBF36zHv1BRf3E4uD2uaGBT1EyydDrbkSmT+v2wAfYg=";

    nativeBuildInputs = [jdk17 maven makeWrapper];

    installPhase = ''
      # create the bin directory
      mkdir -p $out/bin

      # create a symbolic link for the lib directory
      ln -s $fetchedMavenDeps/.m2 $out/lib

      # copy out the JAR
      # Maven already setup the classpath to use m2 repository layout
      # with the prefix of lib/
      cp target/${name}.jar $out/


      # create a wrapper that will automatically set the classpath
      # this should be the paths from the dependency derivation
      makeWrapper ${jdk17}/bin/java $out/bin/${pname} \
              --add-flags "--add-exports java.desktop/sun.awt=ALL-UNNAMED -jar $out/${name}.jar"
    '';
  }
