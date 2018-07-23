package co.edu.escuelaing.is.labinfo.beans;

import co.edu.escuelaing.is.labinfo.entities.Denuncia;
import co.edu.escuelaing.is.labinfo.utiles.Utiles;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

import org.primefaces.event.FlowEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.annotation.PostConstruct;

@ManagedBean
@ApplicationScoped
public class CiudadanoBean implements Serializable {
    private Denuncia denuncia;
    private List<String> municipios;
    private static final String[] COLUMNAS= {
            "edad",
            "zona",
            "arma_empleada",
            "movil_victima",
            "movil_agresor",
            "sexo",
            "estado_civil",
            "escolaridad",
            "clase_de_sitio",
            "cantidad",
            "profesion",
            "pais_de_nacimiento",
            "clase_de_empleado",
            "clase",
            "marca",
            "linea",
            "modelo",
            "color",
            "profesiones"};
    private static final Map<String,String[]> CATEGORIAS = new HashMap<>();
    {
        CATEGORIAS.put("abigeato",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("acciones_subversivas",new String[]{"zona","clase_de_sitio","arma_empleada","movil_victima","codigo_dane","cantidad"});
        CATEGORIAS.put("amenazas",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("delitos_sexuales",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("extorsion",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("homicidios",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("homicidios_en_accidentes_de_transito",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("hurto_a_entidades_comerciales",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","codigo_dane","cantidad"});
        CATEGORIAS.put("hurto_a_entidades_financieras",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","codigo_dane","cantidad"});
        CATEGORIAS.put("hurto_comercio",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","codigo_dane","cantidad"});
        CATEGORIAS.put("hurto_de_automotores",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","clase","marca","linea","modelo","color","cantidad"});
        CATEGORIAS.put("hurto_de_celulares",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","clase","marca","linea","cantidad"});
        CATEGORIAS.put("hurto_personas",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("hurto_residencias",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("huto_de motocicletas",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","clase","marca","linea","modelo","color","cantidad"});
        CATEGORIAS.put("lesiones_accidentes_transito",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("lesiones_personales",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("pirateria_terrestre",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("secuestro",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
        CATEGORIAS.put("terrorismo",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","codigo_dane","cantidad"});
        CATEGORIAS.put("violencia_intrafamiliar",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","cantidad"});
    }
    private boolean[] rederedForm;

    public CiudadanoBean() throws SQLException {
        Utiles.getDepartamentos();
        denuncia=new Denuncia();
    }
    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }


    public List<String> getDepartamentos() throws SQLException {
        ArrayList<String> res= new ArrayList<>();
        for (String s:Utiles.getDepartamentos().keySet()){
            res.add(s);
        }
        res.sort(String::compareTo);
        return res;
    }

    public void onDepartamentoChange() throws SQLException {
        ArrayList<String> res = new ArrayList<>();
        if(denuncia.getDepartamento()!=null && !denuncia.getDepartamento().equals("")){
            if(Utiles.getDepartamentos().containsKey(denuncia.getDepartamento())) {
                for (String s : Utiles.getDepartamentos().get(denuncia.getDepartamento())) {
                    res.add(s);
                }
                res.sort(String::compareTo);
            }
        }else {
            res= new ArrayList<>();
        }
        municipios=res;

    }

    public List<String> getMunicipios() throws SQLException {
        return municipios;
    }







    private MapModel emptyModel;

    private String title;

    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addMarker() {
        Marker marker = new Marker(new LatLng(denuncia.getLat(), denuncia.getLng()), denuncia.getCategoria());
        emptyModel.addOverlay(marker);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marcador Agregado", "" ));
    }

    public void removeMarkers(){
        emptyModel.getMarkers().clear();
    }

    public String getCordenada(){
        return Utiles.getCordsByDepMun(denuncia.getDepartamento()+" "+denuncia.getMunicipio());
    }

    public String onFlowProcess(FlowEvent event) {
        boolean skip = false;
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public boolean isRendered(int x){
        return Arrays.asList(CATEGORIAS.get(denuncia.getCategoria())).contains(COLUMNAS[x]);
    }

    /*
        abigeato,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        acciones_subversivas,zona,clase_de_sitio,arma_empleada,movil_victima,codigo_dane,cantidad
        amenazas,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        delitos_sexuales,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        extorsion,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        homicidios,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        homicidios_en_accidentes_de_transito,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        hurto_a_entidades_comerciales,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,codigo_dane,cantidad
        hurto_a_entidades_financieras,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,codigo_dane,cantidad
        hurto_comercio,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,codigo_dane,cantidad
        hurto_de_automotores,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,clase,marca,linea,modelo,color,cantidad
        hurto_de_celulares,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,clase,marca,linea,cantidad
        hurto_personas,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        hurto_residencias,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        huto_de motocicletas,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,clase,marca,linea,modelo,color,cantidad
        lesiones_accidentes_transito,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        lesiones_personales,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        pirateria_terrestre,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        secuestro,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
        terrorismo,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,codigo_dane,cantidad
        violencia_intrafamiliar,zona,clase_de_sitio,arma_empleada,movil_agresor,movil_victima,edad,sexo,estado_civil,pais_de_nacimiento,clase_de_empleado,profesion,escolaridad,codigo_dane,cantidad
    */
}
