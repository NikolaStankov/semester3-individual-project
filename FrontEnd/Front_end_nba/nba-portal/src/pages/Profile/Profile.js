import PurchaseList from "../../components/PurchaseList";
import useFetch from "../../custom-hooks/useFetch";

const Profile = (props) => {
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
