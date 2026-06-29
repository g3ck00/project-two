import { useState } from 'react'

//
import {useEffect} from "react"
//


import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'

const API="http://localhost:8080/api"


function App() {
    const [count, setCount] = useState(0)

    const [usuarios, setUsuarios] = useState([]);

    const [editandoId, setEditandoId] = useState(null);

    const [loading, setLoading] = useState(true);

    const [rolesAsignados, setRolesAsignados] = useState([]);

    const [readUsuarios, setReadUsuarios]=useState(false);

    const [createUsuario, setCreateUsuario]=useState(false);

    const [mostrarFormularioCreateUsuario, setMostrarFormularioCreateUsuario]=useState(false);

    const [usuario,setUsuario]=useState({
        nombreUsuario:"",
        contrasenna:"",
        email:"",
        activo:true,
    })

    const fetchUsuarios = async () => {
        try {
            const response = await fetch(`${API}/usuarios`);
            const data = await response.json();
            setUsuarios(data.content ?? data);
        } catch (error) {
            console.error("Error al obtener usuarios...");
        }
    }

    const crearUsuario=async()=>{
        if (!nombreUsuario || !contrasenna || !email || !activo) return alert ("Todos los campos son obligatorios.");

        try{
            if (editandoId){
                await fetch(`${API}/usuarios/${editandoId}`, {
                    method: "PUT",
                    headers: {"Content-Type": "application/json",},
                    body: JSON.stringify({nombreUsuario, contrasenna, email, activo}),
                });

                setEditandoId(null);

                } else {
                    await fetch ("http://localhost:8080/api/notas",{
                        method: "POST",
                        headers: {"Content-Type":"application/json",},
                        body: JSON.stringify({nombreUsuario,contrasenna,email,activo}),
                    });
                }

                setNombreUsuario("");
                setContrasenna("");
                setEmail("");;
                setActivo("");
                fetchUsuarios();
            } catch (error) {
            console.error("Error al guardar o editar usuario...");
        }
        }

    useEffect(() => {
        fetchUsuarios();
    }, [])

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

*/}

  return (
      <div>
          <h1>Project Two</h1>
          <h2>Sistema de administración de usuarios</h2>

          <div className={"form"}>
              <button onClick={()=>setMostrarFormularioCreateUsuario(!mostrarFormularioCreateUsuario)}>
                  {mostrarFormularioCreateUsuario ? "Cancelar" : "Crear usuario"}
              </button>

              {mostrarFormularioCreateUsuario && (
                  <div className={"formularioCreateUsuario"}>
                      <h2>Nuevo usuarioo</h2>
                        <label>Nombre de usuario: </label>
                        <input type="text"/>
                        <label>Contraseña: </label>
                        <input type="text"/>
                        <label>Correo electrónico: </label>
                        <input type="text"/>
                        <label>Estado del registro: </label>
                        <input type="text"/>
                      <button>Guardar</button>
                  </div>
              )}

              <button onClick={()=>setReadUsuarios(!readUsuarios)}>
                  {readUsuarios ? "Ocultar usuarios" : "Mostrar usuarios"}
              </button>

              {readUsuarios &&(
                  <ul>
                      {usuarios.map((u)=>(
                            <li key={u.idUsuario}>
                                {u.nombreUsuario} / {u.email} / {u.roles} / {u.activo}
                                / {u.creadoPor} / {u.fechaCreacionRegistrada} / {u.modificadoPor} / {u.fechaModificacion}
                            </li>
                          ))}
                  </ul>
              )}
          </div>

          <div className={"form"}>
              <input
                  type={"text"}
                  placeholder={"Título"}
                  value={"titulo"}
                  onChange={(e)=>setTitulo(e.target.value)}/>

              <input
                  type={"text"}
                  placeholder={"Descripcion"}
                  value={"descripcion"}
                  onChange={(e)=>setDescripcion(e.target.value)}/>

                  <button onClick={"crearNota"}>
                  {editandoId ? "Actualizar nota":"Crear nota"}
                  </button>
          </div>

          {/*
        <h1>Usuarios</h1>
        {usuarios?.length === 0 ? (
            <p>No hay usuarios...</p>
        ) : (
            <ul>
              {usuarios.map((u) => (
                  <li key={u.idUsuario}>
                    {u.nombreUsuario} / {u.email} / {u.roles} / {u.activo}
                    / {u.creadoPor} / {u.fechaCreacionRegistrada} / {u.modificadoPor} / {u.fechaModificacion}
                  </li>
              ))}
            </ul>
        )}

          <h1>Roles asignados</h1>
          {rolesAsignados?.length === 0 ? (
              <p>No hay roles asignados...</p>
          ) : (
              <ul>
                  {rolesAsignados.map((ra) => (
                      <li key={`${ra.usuarioId} / ${ra.rolId}`}>
                          {ra.usuarioId} / {ra.rolId} / {ra.fechaAsignacion} / {ra.activo}
                      </li>
                  ))}
              </ul>
          )}
    */}
      </div>
    );
}

export default App
