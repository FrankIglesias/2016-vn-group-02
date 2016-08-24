package DesignDreamTeamErrorHandlers;

import java.util.Date;

public interface DDTErrorHandler {
	void ejecutarAccion(Date fecha, Runnable proceso);
}
