{
  lib,
  stdenv,
  jdk17,
  maven,
}:
stdenv.mkDerivation {
  name = "maven-repository";
  nativeBuildInputs = [jdk17 maven];
  src = ./.; # or fetchFromGitHub, cleanSourceWith, etc

  buildPhase = ''
    runHook preBuild

    mvn package -Dmaven.repo.local=$out

    runHook postBuild
  '';

  # keep only *.{pom,jar,sha1,nbm} and delete all ephemeral files with lastModified timestamps inside
  installPhase = ''
    runHook preInstall

    find $out -type f \
      -name \*.lastUpdated -or \
      -name resolver-status.properties -or \
      -name _remote.repositories \
      -delete

    runHook postInstall
  '';

  dontFixup = true; # don't do any fixup
  outputHashAlgo = "sha256";
  outputHashMode = "recursive";
  # outputHash = "sha256-yTkdkyFpjH5bjWFucFZkZ7VgeXHi3E2EHR7JQ07F2TU=";
  outputHash = "sha256-/faA370H40emg+Ws/Z/INo5kMyCWw/z9RRwfWibpaCI=";
}
