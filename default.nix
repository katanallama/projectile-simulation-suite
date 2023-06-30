{
  lib,
  stdenv,
  callPackage,
  makeWrapper,
  maven,
  jdk17,
  nix-gitignore,
}: let
  repository = callPackage ./.build-maven-repo.nix {};
in
  stdenv.mkDerivation rec {
    pname = "pss";
    version = "0.1";
    name = "${pname}-${version}";
    src = nix-gitignore.gitignoreSource ["*.nix"] ./.;

    nativeBuildInputs = [jdk17 maven makeWrapper];

    buildPhase = ''
      echo "Building with maven repository ${repository}"
      mvn package --offline -Dmaven.repo.local=${repository}
    '';

    installPhase = ''
      # create the bin directory
      mkdir -p $out/bin

      # create a symbolic link for the lib directory
      ln -s ${repository} $out/lib

      # copy out the JAR
      # Maven already setup the classpath to use m2 repository layout
      # with the prefix of lib/
      cp target/${name}.jar $out/

      # create a wrapper that will automatically set the classpath
      # this should be the paths from the dependency derivation
      makeWrapper ${jdk17}/bin/java $out/bin/${pname} \
              --add-flags "-jar $out/${name}.jar"
    '';
  }
