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

cookbook_file "ntp.conf" do
  path "/etc/ntp.conf"
end

cookbook_file "nginx.default" do
    path "/etc/nginx/sites-available/default"
end

#cookbook_file "project.xml" do
#    path "/etc/nginx/sites-available/default"
#end

execute 'ntp_restart' do
  command 'service ntp restart'
end

execute 'nginx_restart' do
  command 'sudo systemctl restart nginx'
end

execute 'postgresql_create' do
	command 'echo "CREATE DATABASE mydb; CREATE USER vagrant; GRANT ALL PRIVILEGES ON DATABASE mydb TO vagrant;" | sudo -u postgres psql'
end

execute 'tomcat_env_var' do
	command 'echo "export CATALINA_HOME=/opt/tomcat_cmpt470/"> /home/vagrant/.profile'
end


execute 'link_project_foler' do
  command 'sudo rm -rf /opt/tomcat_cmpt470/webapps/project'
	command 'ln -s /vagrant/Deploy /opt/tomcat_cmpt470/webapps/project'
end

tomcat_service 'cmpt470' do
  action [:start, :enable]
  env_vars [{ 'CATALINA_BASE' => '/opt/tomcat_cmpt470/' }]
  sensitive true
end


