import LoginForm from "../components/LoginForm";
import { useLocation, useNavigate } from "react-router-dom";

const Login = (props) => {
  const location = useLocation();
  let navigate = useNavigate();

  return (
    <LoginForm
      updateLoggedUserProps={props.updateLoggedUserProps}
      locationProps={location}
      navigateProps={navigate}
    />
  );
};

export default Login;
