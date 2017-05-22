import Api from './Api'




class CategoriesApi extends Api {

	getAllCategories() {
		return this.call("/categories");
	}

}

export default CategoriesApi;
