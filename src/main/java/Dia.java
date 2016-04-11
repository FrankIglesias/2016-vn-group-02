public enum Dia {
    LUNES {
        @Override
        public int getDia() { return 1; }
    }, 
    MARTES {
        @Override
        public int getDia() { return 2; }
    },
    MIEROLES {
        @Override
        public int getDia() { return 3; }
    },
    JUEVES {
        @Override
        public int getDia() { return 4; }
    },
	VIERNES {
        @Override
        public int getDia() { return 5; }
    },
    SABADO {
        @Override
        public int getDia() { return 6; }
    },
    DOMINGO {
        @Override
        public int getDia() { return 7; }
    },;
	public abstract int getDia();

}