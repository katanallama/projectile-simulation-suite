{ lib, stdenv, maven }:

stdenv.mkDerivation {
  name = "maven-repository";
  buildInputs = [ maven ];
  src = ./.; # or fetchFromGitHub, cleanSourceWith, etc

  preInstall = ''
    mvn clean test
  '';

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
  # replace this with the correct SHA256
  outputHash = "sha256-XauJ6Pox/e/Z8ZfCS8T1tCH2yx6dwEP++vw+NkTFP9k=";
}
