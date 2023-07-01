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

    mvn artifact:check-buildplan
    mvn dependency:resolve -Dclassifier=sources
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
  # outputHash = "sha256-Pe/Xdgvmfdlgrrpv6cINc2FodaGvo10FoWl280h4OXw=";
  outputHash = "sha256-vd4lwUie8r1wSnfkqLnxuBj+yacy2fPhCQYVZUJArpw=";
}
