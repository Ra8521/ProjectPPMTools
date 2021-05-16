import axios from "axios";
import { DELETE_PROJECT, GET_ERRORS } from "./types";
import { GET_PROJECTS, GET_PROJECT } from "./types";

export const createProject = (project, history) => async dispatch => {
  try {
    const res = await axios.post("api/project", project);
    history.push("/dashboard");
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    //console.log(err);
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

/* CRUD */
/* CREATE Operation */
export const getProjects = () => async dispatch => {

    const res = await axios.get("/api/project/all");
    /* Dispatch to our redux store */
    dispatch({
      type: GET_PROJECTS,
      payload: res.data
    });

}

/*RETRIEVE Operation*/
export const getProject = (id,history) => async dispatch => {
  try{
  const res = await axios.get(`api/project/${id}`);
    /* dispatching actions to the store. */
    
    dispatch({
      type: GET_PROJECT,
      payload: res.data
    });
  }
  catch(err){
        history.push("/dashboard");
  }

}

/* DELETE Operation */

export const deleteProject = id => async dispatch => {
  if (
    window.confirm(
      "Are you sure? This will delete the project and all the data related to it"
    )
  ) {
    await axios.delete(`/api/project/${id}`);
    dispatch({
      type: DELETE_PROJECT,
      payload: id
    });
  }
};