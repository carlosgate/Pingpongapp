package test;

public class Usuario {
    private String nombre;
    private String email;
    private int edad;

    // Constructor privado que recibe un builder
    private Usuario(Builder builder) {
        this.nombre = builder.nombre;
        this.email = builder.email;
        this.edad = builder.edad;
    }

    // Clase estática interna: el Builder
    public static class Builder {
        private String nombre;
        private String email;
        private int edad;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setEdad(int edad) {
            this.edad = edad;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    // Getters para acceder a los atributos
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getEdad() {
        return edad;
    }
}

