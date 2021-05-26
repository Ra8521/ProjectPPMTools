import axios from "axios";
import setJWTtoken from "../securityUtils/setJWTtoken";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import jwt_decode from "jwt-decode"
export const createNewUser = (newUser, history) => async dispatch => {
  try {
    await axios.post("/api/users/register", newUser);
    history.push("/login");
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const login = LoginRequest => async dispatch =>{

  try {
    //post => Login Request
    const res = await axios.post("/app/users/login", LoginRequest)
  //extract token from res.data
   const { token } = res.data

  //store token in localStorage
  localStorage.setItem("JWTtoken", token)
  //set our token in header **** // secured route
  setJWTtoken(token)
  //decode token on react
  const decoded = jwt_decode(token);
  //dispatch to our secured reducer
  dispatch({
    type: SET_CURRENT_USER,
    payload:decoded
  });
  } catch (err) {
     dispatch({
       type:GET_ERRORS,
       payload:err.response.data
     });
  }
  
}