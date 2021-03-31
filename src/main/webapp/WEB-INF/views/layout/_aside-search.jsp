<%@ page pageEncoding="utf-8"%>

<div class="panel panel-info">
	<div class="panel-heading" style="    color: #ffffff;
	background-color: #337ab7;">
		<h3 class="panel-title">
			<span class="glyphicon glyphicon-search"></span> Search
		</h3>
	</div>
	<div class="panel-body">
		<form action="/product/list-by-keywords" method="get">
			<div class="input-group">
				<input name="keywords" placeholder="Keywords?" class="form-control">
				<div class="input-group-addon">
					<span class="glyphicon glyphicon-search"></span>
				</div>
			</div>
		</form>
	</div>
</div>