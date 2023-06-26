{
  description = "A Nix flake for building a projectile simulation suite";

  inputs = {nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";};

  outputs = {
    self,
    nixpkgs,
    ...
  }: let
    system = "x86_64-linux";

    pkgs = import nixpkgs {inherit system;};

    overlay = self: super: {
      projectile-simulation-suite = self.callPackage ./default.nix {};
      jdt-language-server = self.callPackage ./jdt-language-server.nix {};
    };

    appPkgs = pkgs.extend overlay;
  in
    with appPkgs; {
      packages.${system}.default = appPkgs.projectile-simulation-suite;

      devShells.${system}.default = mkShellNoCC {
        name = "java";
        buildInputs = [jdk17 maven jdt-language-server];

        shellHook = ''
          export JAVA_HOME=${jdk17}
          export JDTLS_PATH=${jdt-language-server}/share/java/
        '';
      };
    };
}
