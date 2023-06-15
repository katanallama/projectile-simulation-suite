{
  description = "A Nix flake for building a projectile simulation suite";

  inputs = { nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable"; };

  outputs = { self, nixpkgs, ... }:

    let
      system = "x86_64-linux";

      pkgs = import nixpkgs { inherit system; };

      overlay = self: super: {
        projectile-simulation-suite = self.callPackage ./default.nix { };
        jdt-language-server = self.callPackage ./jdt-language-server.nix { };
      };

      appPkgs = pkgs.extend overlay;

    in {
      defaultPackage.${system} = appPkgs.projectile-simulation-suite;

      devShell.${system} = appPkgs.mkShellNoCC {
        name = "java";
        buildInputs =
          [ appPkgs.jdk17 appPkgs.maven appPkgs.jdt-language-server ];

        defaultPackage = appPkgs.projectile-simulation-suite;

        shellHook = ''
          export JAVA_HOME=${appPkgs.jdk17}
          export JDTLS_PATH=${appPkgs.jdt-language-server}/share/java/
        '';
      };
    };
}
