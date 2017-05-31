import Api from './Api'



class CategoriesApi extends Api {

	createCategory(category) {
		return this.call("/categories", {
			method: 'POST',
			headers: {
				'Content-Type': "application/json; charset=UTF-8"
			},
			body: JSON.stringify(category)
		})
	}

	getAllCategories() {
		return this.call("/categories");
	}

	getCategoryById(categoryId) {
		return this.call("/categories/"+categoryId);
	}

	updateCategory(id, category) {
		return this.call("/categories/"+id, {
			method: 'PUT',
			headers: {
				'Content-Type': "application/json; charset=UTF-8"
			},
			body: JSON.stringify(category)
		})
	}

	deleteCategory(categoryId) {
		return this.call("/categories/"+categoryId, {
			method: 'DELETE',
		})
	}

}

export default CategoriesApi;
