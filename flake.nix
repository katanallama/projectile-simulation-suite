{
  description = "A Nix flake for building a projectile simulation suite";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";

  outputs = {
    self,
    nixpkgs,
    ...
  }: let
    system = "x86_64-linux";
    pkgs = nixpkgs.legacyPackages.${system};
  in {
    overlays.default = final: _: removeAttrs self.packages.${final.system} ["default"];
    #overlay is deprecated ( i still use it sometimes though)
    overlay = self.overlays.default;

    packages.${system} = {
      projectile-simulation-suite = pkgs.callPackage ./. {};
      jdt-language-server = pkgs.callPackage ./jdt-language-server.nix {};
      default = self.packages.${system}.projectile-simulation-suite;
    };

    devShells.${system}.default = pkgs.mkShellNoCC {
      packages = [
        pkgs.jdk17
        self.packages.${system}.jdt-language-server
      ];
      inputsFrom = [
        self.packages.${system}.projectile-simulation-suite
      ];
      JAVA_HOME = pkgs.jdk17;
      JDTLS_PATH = "${pkgs.jdt-language-server}/share/java/}";
      LD_LIBRARY_PATH = "${pkgs.xorg.libXxf86vm}/lib/libXxf86vm.so.1";
    };
    formatter.${system} = pkgs.alejandra;
  };
}
