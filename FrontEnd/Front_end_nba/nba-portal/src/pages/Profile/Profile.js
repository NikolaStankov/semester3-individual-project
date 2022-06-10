import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import PurchaseList from "../../components/PurchaseList";
import useFetch from "../../custom-hooks/useFetch";

const Profile = (props) => {
  let navigate = useNavigate();

  if (!props.loggedUser) {
    navigate("/login");
  }

  const purchasesURL =
    "http://localhost:8080/users/" + props.loggedUser.id + "/purchases";

  const { data: purchases } = useFetch(purchasesURL);

  return (
    <>
      <h1>{props.loggedUser.username}'s purchases:</h1>
      {purchases && <PurchaseList purchases={purchases} />}
    </>
  );
};

export default Profile;
