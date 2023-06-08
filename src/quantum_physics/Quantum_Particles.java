package quantum_physics;


public class Quantum_Particles {
	
	public static void main(String[] args) {
		Electron el = new Electron();
		Gluon gln = new Gluon();
		
		el.print_self();
		gln.print_self();
	}
}

class Particle{
	double mass,charge,spin;
	
	Particle(double particles_mass, double particles_charge, double particles_spin){
		this.mass=particles_mass;
		this.charge=particles_charge;
		this.spin=particles_spin;
	}
	void print_self() {
		System.out.printf("\n%.2f\n%.2f\n%.2f\n",((Particle)this).mass,((Particle)this).charge,((Particle)this).spin);
	}
}

//---------_____________Types____________------------//
class Fermion extends Particle{
	
	Fermion(double particles_mass, double particles_charge, double particles_spin){
		super(particles_mass,particles_charge,particles_spin);
	}
}
class Boson extends Particle{
	
	Boson(double particles_mass, double particles_charge, int particles_spin){
		super(particles_mass,particles_charge,(double)particles_spin);
	}
}

class Lepton extends Fermion{
	Lepton(double particles_mass, int particles_charge, double particles_spin){
		super(particles_mass,(double)particles_charge,particles_spin);
	}
}
class Quark extends Fermion{
	Quark(double particles_mass, double particles_charge, double particles_spin){
		super(particles_mass,particles_charge,particles_spin);
	}
}

class Neutrino extends Fermion{
	Neutrino(double particles_mass){
		super(particles_mass,0,0.5);
	}
}


//-------------_______________Particles_______________----------------//
class Gluon extends Boson{
	Gluon(){
		super(0,0,1);
	}
}
class Photon extends Boson{
	Photon(){
		super(0,0,1);
	}
}
class Z extends Boson{
	Z(){
		super(91190,0,1);
	}
}
class W_plus extends Boson{
	W_plus(){
		super(80390,1,1);
	}
}
class W_minus extends Boson{
	W_minus(){
		super(80390,-1,1);
	}
}
class Higgs extends Boson{
	Higgs(){
		super(124970,0,0);
	}
}

class Electron extends Fermion{
	Electron(){
		super(0.5,-1,0.5);
	}
}

