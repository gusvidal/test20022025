package cl.gvidal.neoris.utils;

public class Constants {

    //Roles
    public static final String AUTHORITY_USER = "USER";
    public static final String AUTHORITY_ADMIN = "ADMIN";

    //Respuestas
    public static final String USER_CREATED_SUCCESFULLY = "Usuario creado en forma exitosa";
    public static final String USER_NOT_CREATED = "Ha ocurrido un error al momento de crear el usuario";

    //Validaciones
    public static final String USER_EXISTS = "El usuario ya se encuentra registrado";
    public static final String MAIL_EXISTS = "El correo ya se encuentra registrado";
    public static final String MAIL_INVALID = "El Email ingresado no posee el formato adecuado";
    public static final String PASSWORD_INVALID = "El password debe contener al menos una letra mayuscula, numeros y caracteres alfanumericos";

    //login
    public static final String INVALID_CREDENTIALS = "Error en proceso de login, favor revise sus credenciales";
    public static final String LOGIN_ERROR = "Error en proceso de logeo ";

    //lista de usuarios
    public static final String EMPTY_USERS_LIST = "El sistema no cuenta con usuarios registrados";
}
