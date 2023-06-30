{
  lib,
  stdenv,
  maven,
}:
stdenv.mkDerivation {
  name = "maven-repository";
  nativeBuildInputs = [maven];
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
  # replace this with the correct SHA256, otherwise it will fail mysteriously
  outputHash = "sha256-hb4+yJidKrDkIrgYsidQfHMFyl9l1jcaP5u+c8PVjIg=";
}
