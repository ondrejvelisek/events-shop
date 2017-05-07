import React, { Component } from 'react';
import { Link, IndexLink, withRouter } from 'react-router'

class NavItem extends Component {
	render () {
		// keep unused props because they are excluded from last spread props var
		const { router, index, to, onlyActiveOnIndex, children, params, location, routes, key, ...props } = this.props;

		let isActive;
		if (router.isActive('/', onlyActiveOnIndex) && index) {
			isActive = true;
		} else {
			isActive = router.isActive(to);
		}

		const LinkComponent = index ? IndexLink : Link;

		return (
			<li className={isActive ? 'active' : ''}>
				<LinkComponent to={to} {...props}>{children}</LinkComponent>
			</li>
		)
	}
}

NavItem = withRouter(NavItem);

export default NavItem;