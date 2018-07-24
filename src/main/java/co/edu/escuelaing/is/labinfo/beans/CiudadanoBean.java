package co.edu.escuelaing.is.labinfo.beans;

import co.edu.escuelaing.is.labinfo.entities.Denuncia;
import co.edu.escuelaing.is.labinfo.utiles.Utiles;

import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class CiudadanoBean implements Serializable {
    private Denuncia denuncia;
    private String departamento, municipio, barrio, delito;
    private Date fecha,hora;
    private List<String> municipios;
    public static final String[] COLUMNAS= {
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
            "profesiones",
            "categoria",
            "barrio"};
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
        CATEGORIAS.put("huto_de_motocicletas",new String[]{"zona","clase_de_sitio","arma_empleada","movil_agresor","movil_victima","edad","sexo","estado_civil","pais_de_nacimiento","clase_de_empleado","profesion","escolaridad","codigo_dane","clase","marca","linea","modelo","color","cantidad"});
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
        Utiles.getCategorias();
        Utiles.armarDic();
        denuncia=new Denuncia();
    }
    public void load() throws SQLException {
        Utiles.getDepartamentos();
        Utiles.getCategorias();
        Utiles.armarDic();
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
        return Arrays.asList(CATEGORIAS.get(denuncia.getCategoria().replace(" ","_"))).contains(COLUMNAS[x]);
    }

    public List<String> autoCompleteMethod0(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[0])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod1(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[1])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod2(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[2])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod3(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[3])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod4(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[4])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod5(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[5])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod6(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[6])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod7(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[7])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod8(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[8])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod9(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[9])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod10(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[10])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod11(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[11])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod12(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[12])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod13(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[13])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod14(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[14])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod15(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[15])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod16(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[16])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod17(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[17])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod18(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[18])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod19(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[19])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }public List<String> autoCompleteMethod20(String q) throws SQLException {
        List<String> res = new ArrayList<>();
        for(String x: Utiles.getColumnValues().get(COLUMNAS[20])) if (x.toLowerCase().startsWith(q.toLowerCase())){
            res.add(x);
        }
        return res;
    }

    public void upload() throws SQLException {
        Utiles.insertCrimen(denuncia,"DatosAlaU");
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDelito() {
        return delito;
    }

    public void setDelito(String delito) {
        this.delito = delito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
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
