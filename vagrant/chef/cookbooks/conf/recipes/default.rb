# Make sure the Apt package lists are up to date, so we're downloading versions that exist.
cookbook_file "apt-sources.list" do
  path "/etc/apt/sources.list"
end
execute 'apt_update' do
  command 'apt-get update'
end

# Base configuration recipe in Chef.
package "wget"
package "ntp"
package "nginx"
package "postgresql"

include_recipe 'java'

tomcat_install 'cmpt470' do
  version '8.0.36'
end

execute 'tomcat_env_var' do
  command 'echo "export CATALINA_HOME=/opt/tomcat_cmpt470"> /home/vagrant/.profile'
end

execute 'project_home_env_var' do
  command 'echo "export PROJECT_HOME=/vagrant"> /home/vagrant/.profile'
end

execute 'psql_pw_env_var' do
  command 'echo "export PGPASSWORD=password"> /home/vagrant/.profile'
end

cookbook_file "ntp.conf" do
  path "/etc/ntp.conf"
end

cookbook_file "nginx.default" do
    path "/etc/nginx/sites-available/default"
end

cookbook_file "postgresql-42.2.2.jar" do
    path "/opt/tomcat_cmpt470/lib/postgresql-42.2.2.jar"
end

cookbook_file "setenv.sh" do
    path "/opt/tomcat_cmpt470/bin/setenv.sh"
end

cookbook_file "project.xml" do
    path "/opt/tomcat_cmpt470/conf/project.xml"
end

execute 'ntp_restart' do
  command 'service ntp restart'
end

execute 'nginx_restart' do
  command 'sudo systemctl restart nginx'
end

execute 'postgresql_create' do
	command 'echo "CREATE USER projectuser PASSWORD \'password\'; CREATE DATABASE projectDB; GRANT ALL PRIVILEGES ON DATABASE projectDB TO projectuser;" | sudo -u postgres psql'
  ignore_failure true
end

include_recipe 'conf::dbscripts'

execute 'link_project_foler' do
    command 'sudo rm -r /opt/tomcat_cmpt470/webapps/project'
    ignore_failure true
end

execute 'link_project_folder' do
    command 'ln -s /vagrant/Deploy /opt/tomcat_cmpt470/webapps/project'
end

tomcat_service 'cmpt470' do
  action [:start, :enable, :restart]
  env_vars [{ 'CATALINA_BASE' => '/opt/tomcat_cmpt470' }]
  sensitive true
end


