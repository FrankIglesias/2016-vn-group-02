package BancoExterno;

import java.util.List;

import TypePois.Banco;

public interface ApiDeBancoInterface {
	public List<Banco> obtenerBancoDesdeString();
}
