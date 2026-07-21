import {useEffect, useState} from 'react'


import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'

import Button from "./components/ui/Button.tsx"

const API="http://localhost:8080/api"

function App() {
    const [usuarios, setUsuarios] = useState([]);

    const [editandoId, setEditandoId] = useState(null);

    const [loading, setLoading] = useState(true);

    const [readUsuarios, setReadUsuarios]=useState(false);

    const [createUsuario, setCreateUsuario]=useState(false);

    const [mostrarFormularioCreateUsuario, setMostrarFormularioCreateUsuario]=useState(false);

    const [mostrarFormularioBuscarUsuario, setMostrarFormularioBuscarUsuario]=useState(false);

    const [errores, setErrores]=useState({});

    const [pagina, setPagina]=useState(0);
    const [totalPaginas, setTotalPaginas]=useState(0);

    const [mensajeExito, setMensajeExito]=useState("");

    const [idBusqueda,setIdBusqueda]=useState("");
    const [errorBusqueda, setErrorBusqueda]=useState("");

    const [usuario,setUsuario]=useState({
        nombreUsuario:"",
        contrasenna:"",
        email:"",
        activo:false,
        creadoPor:"",
        fechaCreacionRegistrada:"",
        modificadoPor:"",
        fechaModifcacion:""
    })

    const [usuarioEncontrado, setUsuarioEncontrado]=useState({
        nombreUsuario:"",
        contrasenna:"",
        email:"",
        activo:false,
        creadoPor:"",
        fechaCreacionRegistrada:"",
        modificadoPor:"",
        fechaModifcacion:""
    })

    const fetchUsuarios = async (page=0) => {
        const response=await fetch(`${API}/usuarios?page=${page}&size=20`);
        const data=await response.json();

        try {
            setUsuarios(data.content);
            setPagina(data.number);
            setTotalPaginas(data.totalPages);

            console.log(data);

        } catch (error) {
            console.error("Error al obtener usuarios...");
        }
    }

    const crearUsuario=async()=>{

        setErrores({});

        setMensajeExito("");

        if (!usuario.nombreUsuario || !usuario.contrasenna || !usuario.email
            || !usuario.activo) return alert ("Todos los campos son obligatorios.");

        try{

            let response;

            if (editandoId){
                response=await fetch(`${API}/usuarios/${editandoId}`, {
                    method: "PUT",
                    headers: {"Content-Type": "application/json",},
                    body: JSON.stringify(usuario),
                });

                setEditandoId(null);

                } else {
                    response=await fetch ("http://localhost:8080/api/usuarios",{
                        method: "POST",
                        headers: {"Content-Type":"application/json",},
                        body: JSON.stringify(usuario),
                    });
                }

            if (!response.ok){

                if (response.status===400){
                    const errores=await response.json();

                    console.log(errores);

                    setErrores(errores);

                    return;
                }

                throw new Error("Error al guardar usuario.");
            }

            setMensajeExito("Usuario guardado con éxito.")

            setUsuario({
                nombreUsuario:"",
                contrasenna:"",
                email:"",
                activo:false
            })

            fetchUsuarios();

        } catch (error) {
            console.error("Error al guardar o editar usuario...");
        }
    }

    /*
    const updateUsuario=async()=>{
        setErrores({});

        setMensajeExito("");

        try{

            let response;

            if (editandoId){
                response=await fetch(`${API}/usuarios/${editandoId}`, {
                    method: "UPDATE",
                    headers: {"Content-Type": "application/json",},
                    body: JSON.stringify(usuario),
                });

                setEditandoId(null);

            } else {
                response=await fetch ("http://localhost:8080/api/usuarios",{
                    method: "POST",
                    headers: {"Content-Type":"application/json",},
                    body: JSON.stringify(usuario),
                });
            }

            if (!response.ok){

                if (response.status===400){
                    const errores=await response.json();

                    console.log(errores);

                    setErrores(errores);

                    return;
                }

                throw new Error("Error al editar usuario...");
            }

            setMensajeExito("Usuario guardado con éxito.")

            setUsuario({
                nombreUsuario:"",
                contrasenna:"",
                email:"",
                activo:false
            })

            fetchUsuarios();

        } catch (error) {
            console.error("Error al guardar o editar usuario...");
        }
    }
     */

    const buscarUsuario=async()=>{
        setErrorBusqueda("");

        try {
            const response = await fetch(`${API}/usuarios/${idBusqueda}`);

            if (!response.ok) {
                setUsuarioEncontrado({
                    nombreUsuario: "",
                    contrasenna: "",
                    email: "",
                    activo: false
                });

                setErrorBusqueda("El usuario no existe...");
                return;
            }

            const data = await response.json();

            setUsuarioEncontrado({
                nombreUsuario: data.nombreUsuario,
                contrasenna: data.contrasenna,
                email: data.email,
                activo: data.activo,
                creadoPor: data.creadoPor,
                fechaCreacionRegistrada: data.fechaCreacionRegistrada,
                modificadoPor: data.modificadoPor,
                fechaModifcacion: data.fechaModifcacion
            });

            console.log(usuarioEncontrado);

        } catch (error) {
            setErrorBusqueda("Error en la consulta...")
        }
    }

    useEffect(() => {
        fetchUsuarios(pagina);
    }, [pagina])

    {/*
  useEffect(() => {
    fetch(`${API}/usuarios`)
        .then((res) => {
          if (!res.ok) throw new Error("Error al obtener usuarios...");
          return res.json();
        })
        .then((data) => {
          setUsuarios(data.content ?? data); // soporta Page o List
        })
        .catch((err) => console.log(err))
        .finally(() => setLoading(false));
  }, []);
  */}

    useEffect(() => {
        fetch(`${API}/roles-asignados`)
            .then((res) => {
                if (!res.ok) throw new Error("Error al obtener los roles asignados...");
                return res.json();
            })
            .then((data) => {
                setRolesAsignados(data.content ?? data); // soporta Page o List
            })
            .catch((err) => console.log(err))
            .finally(() => setLoading(false));
    }, []);

    if (loading) {
        return <h2>Cargando...</h2>
    }

  return (
      <div>
          <h1>Project Two</h1>
          <h2>Sistema de administración de usuarios</h2>

          <Button onClick={()=>setMostrarFormularioCreateUsuario(!mostrarFormularioCreateUsuario)}>
              {mostrarFormularioCreateUsuario ? "Cancelar" : "Crear usuario"}
          </Button>

          {mostrarFormularioCreateUsuario && (
              <div className={"formularioCreateUsuario"}>
                  <h2>Nuevo usuario</h2>

                  <label>Nombre de usuario: </label>
                  <input type="text" value={usuario.nombreUsuario}
                         onChange={(e)=>
                             setUsuario({...usuario, nombreUsuario: e.target.value})
                         }/><br></br>

                  <label>Contraseña: </label>
                  <input type="text"
                         value={usuario.contrasenna}
                         onChange={(e)=>
                             setUsuario({...usuario, contrasenna : e.target.value})
                         }/><br></br>

                  <label>Correo electrónico: </label>
                  <input type="text"
                         value={usuario.email}
                         onChange={(e)=>
                             setUsuario({...usuario, email : e.target.value})
                         }/><br></br>

                  {errores.email &&
                      <p className={"error"}>{errores.email}</p>}

                  <label>Estado del registro: </label>
                  <input type="checkbox"
                         checked={usuario.activo}
                         onChange={(e)=>
                             setUsuario({...usuario, activo : e.target.checked})
                         }/><br></br>

                  <button onClick={crearUsuario}>
                      Guardar
                  </button>

                  {mensajeExito&&(
                      <p className={"exito"}>{mensajeExito}</p>
                  )}

              </div>
          )}

          <div className={"form"}>
              <Button onClick={()=>setMostrarFormularioBuscarUsuario(!mostrarFormularioBuscarUsuario)}>
                  {mostrarFormularioBuscarUsuario ? "Cancelar" : "Buscar usuario"}
              </Button>

              {mostrarFormularioBuscarUsuario && (
                  <div className={"formularioBuscarUsuario"}>
                      <h2>Buscar usuario</h2>

                      <label>ID de usuario:</label>
                      <input type="text" value={idBusqueda}
                             onChange={(e)=>setIdBusqueda(e.target.value)}
                      />

                      <button onClick={buscarUsuario}>
                          Buscar
                      </button><br></br>

                      {errorBusqueda && (
                          <p className={"error"}>{errorBusqueda}</p>
                      )}

                      {usuarioEncontrado.nombreUsuario &&(
                          <>
                              <label>Nombre de usuario</label>
                              <input
                                  type={"text"}
                                  value={usuarioEncontrado.nombreUsuario}
                                  readOnly
                              /><br></br>

                              <label>Email</label>
                              <input
                                  type={"text"}
                                  value={usuarioEncontrado.email}
                                  readOnly
                              /><br></br>

                              <label>Activo</label>
                              <input
                                  type={"checkbox"}
                                  checked={usuarioEncontrado.activo}
                                  readOnly
                              /><br></br>

                              <label>Creado por</label>
                              <input
                                  type={"text"}
                                  value={usuarioEncontrado.creadoPor}
                                  readOnly
                              /><br></br>

                              <label>Fecha de modificación</label>
                              <input
                                  type={"text"}
                                  value={usuarioEncontrado.fechaCreacionRegistrada}
                                  readOnly
                              /><br></br>

                              <label>Modificado por</label>
                              <input
                                  type={"text"}
                                  value={usuarioEncontrado.modificadoPor}
                                  readOnly
                              /><br></br>

                              <label>fechaModificacion</label>
                              <input
                                  type={"text"}
                                  value={usuarioEncontrado.fechaModifcacion}
                                  readOnly
                              /><br></br>
                          </>
                      )}
                  </div>
              )}

              <Button onClick={()=>{return 0;}}>
                  {mostrarFormularioCreateUsuario ? "Cancelar" : "Modificar usuario [DUMMY]"}
              </Button>

              <Button onClick={()=>{return 0;}}
                      style={{backgroundColor:"#ff0000"}}>
                  {mostrarFormularioCreateUsuario ? "Cancelar" : "Eliminar usuario [DUMMY]"}
              </Button>

          </div>

          <div className={"container mt-4"}>
              <h2>Usuarios</h2>

              <Button disabled={pagina===0}
                      onClick={()=>setPagina(pagina-1)}
              >Anterior</Button>

              <span>Pagina {pagina+1} de {totalPaginas}</span>

              <Button disabled={pagina+1>=totalPaginas}
                      onClick={()=>setPagina(pagina+1)}
              >Siguiente</Button>

              <br></br>
              <br></br>

              <div className={"rounded overflow-hidden"}>
              <table
                  className={"table table-striped table-hover table-bordered table-dark rounded align-middle"}
                    style={{tableLayout: "fixed", width: "100%"}}
              >
                  <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>Roles</th>
                        <th>Estado del registro</th>
                        <th>Creador</th>
                        <th>Fecha de creación</th>
                        <th>Modificador</th>
                        <th>Fecha de modificación</th>
                    </tr>
                  </thead>

                  <tbody>
                  {usuarios.map((u)=>(
                      <tr key={u.idUsuario} style={{height: "50px"}}>
                          <td style={{ overflowWrap: "break-word" }}>{u.idUsuario}</td>
                          <td style={{ overflowWrap: "break-word" }}>{u.nombreUsuario}</td>
                          <td style={{ overflowWrap: "break-word" }}>{u.email}</td>
                          <td style={{ overflowWrap: "break-word" }}>{u.roles}</td>
                          <td style={{ overflowWrap: "break-word" }}>{u.activo}</td>
                          <td style={{ overflowWrap: "break-word" }}>{u.creadoPor}</td>
                          <td style={{ overflowWrap: "break-word" }}>{u.fechaCreacionRegistrada}</td>
                          <td style={{ overflowWrap: "break-word" }}>{u.modificadoPor}</td>
                          <td style={{ overflowWrap: "break-word" }}>{u.fechaModificacion}</td>
                      </tr>
                  ))}
                  </tbody>
              </table>
              </div>

              import { render, screen } from "@testing-library/react";
              import Button from "./Button";

              test("renders button", () => {
              render(<Button>Save</Button>);

              expect(
              screen.getByText("Save")
              ).toBeInTheDocument();
          });

          </div>

          {/*}
          <div className={"form"}>
              <button onClick={()=>setMostrarFormularioUpdateUsuario(!mostrarFormularioUpdateUsuario)}>
                  {mostrarFormularioUpdateUsuario ? "Cancelar" : "Modificar usuario"}
              </button>

              {mostrarFormularioUpdateUsuario && (
                  <div className={"formularioUpdateUsuario"}>
                      <h2>Modificar usuario</h2>

                      <label>ID de usuario:</label>
                      <input type="text" value={idBusqueda}
                             onChange={(e)=>setIdBusqueda(e.target.value)}
                      />

                      <button onClick={buscarUsuario}>
                          Buscar
                      </button><br></br>

                      {errorBusqueda && (
                          <p className={"error"}>{errorBusqueda}</p>
                      )}

                      <label>Nombre de usuario: </label>
                      <input type="text" value={usuario.nombreUsuario}
                                    onChange={(e)=>
                                    setUsuario({...usuario, nombreUsuario: e.target.value})
                                    }/><br></br>

                        <label>Contraseña: </label>
                        <input type="text"
                        value={usuario.contrasenna}
                                onChange={(e)=>
                                setUsuario({...usuario, contrasenna : e.target.value})
                                }/><br></br>

                      <label>Correo electrónico: </label>
                        <input type="text"
                        value={usuario.email}
                                onChange={(e)=>
                                setUsuario({...usuario, email : e.target.value})
                                }/><br></br>

                      {errores.email &&
                      <p className={"error"}>{errores.email}</p>}

                        <label>Estado del registro: </label>
                        <input type="checkbox"
                        checked={usuario.activo}
                                onChange={(e)=>
                                setUsuario({...usuario, activo : e.target.checked})
                                }/><br></br>


                      <button onClick={crearUsuario}>
                            Guardar
                        </button>

                      {mensajeExito&&(
                          <p className={"exito"}>{mensajeExito}</p>
                      )}

                  </div>
              )}
              {*/}

          {/*}
              <h2>Usuarios</h2>
              <ul>
                  {usuarios.map((u)=>(
                      <li key={u.idUsuario}>
                          {u.idUsuario} --- {u.nombreUsuario} --- {u.email} --- {u.roles} --- {u.activo}
                          --- {u.creadoPor} --- {u.fechaCreacionRegistrada} --- {u.modificadoPor} --- {u.fechaModificacion}
                      </li>
                  ))}
              </ul>
              {*/}
          </div>
    );
}

export default App
