package br.gov.prefeitura.sgm.service;

public enum UsuarioDTO {
	
	INVALIDO("Inválido", "", ""), 
    TCC("tcc", "123", "dcbacadf485c141a2b9b0028f2c0b2e1"),
	VICTOR("victor", "321", "ffc150a160d37e92012c196b6af4160d");

	private String login;
	private String senha;
	private String token;

    private UsuarioDTO(String login, String senha, String token)
    {
    	this.login = login;
    	this.senha = senha;
    	this.token = token;
    }

    public static UsuarioDTO getEnum(String login, String senha)
    {
        if (login == null && senha == null)
            return INVALIDO;

        for (UsuarioDTO item : UsuarioDTO.values())
        {
            if (item.login.equals(login) && item.senha.equals(senha))
                return item;
        }
        return INVALIDO;
    }
    
    public static UsuarioDTO getEnum(String token)
    {
        if (token == null)
            return null;

        for (UsuarioDTO item : UsuarioDTO.values())
        {
            if (item.token.equals(token))
                return item;
        }
        return INVALIDO;
    }
    
    public String getLogin() {
		return login;
	}
    
    public String getToken() {
		return token;
	}
    
    public String getSenha() {
		return senha;
	}
}
